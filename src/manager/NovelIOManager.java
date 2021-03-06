package manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import exception.EditorIOException;
import javafx.scene.control.TreeItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import model.IndexItem;
import model.Part;
import model.Section;
import model.Title;

public class NovelIOManager {

	public static final FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("すべてのファイル", "*.*");
	public static final FileChooser.ExtensionFilter novelFilter = new FileChooser.ExtensionFilter("小説ファイル(*.novel)", "*.novel");
	public static final FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("テキストファイル(*.txt)", "*.txt");

	public NovelIOManager() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Title load(File novelFile) {
		Document document = null;
		try {
			document = DocumentBuilderFactory
									.newInstance()
									.newDocumentBuilder()
									.parse(novelFile);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}

		document.getDocumentElement().normalize();

		Element novel = document.getDocumentElement();
		String novelTitle = novel.getAttribute("title");
		Title title = new Title(novelTitle);

		NodeList items = novel.getChildNodes();
		constructIndex(items, title);
		return title;
	}

	private void constructIndex(NodeList items, Section parent) {
		for (int i = 0; i < items.getLength(); i++) {
			Element item = (Element) items.item(i);

			if (item.getNodeType() == Node.ELEMENT_NODE) {
				if (item.getNodeName().equals("Section")) {
					Section childSection = new Section(item.getAttribute("title"));
					constructIndex(item.getChildNodes(), childSection);
					parent.addSection(childSection);
				} else if (item.getNodeName().equals("Part")) {
					String partTitle = item.getAttribute("title");
					Part part = new Part(partTitle);
					part.setContext(item.getTextContent());
					parent.addPart(part);
				}
			}
		}
	}

	public File save(File novelFile, Title root) throws EditorIOException{

		if (novelFile == null) {
			FileChooser folder = new FileChooser();
			folder.setTitle("Save file");
			folder.getExtensionFilters().addAll(novelFilter, allFilter);
			folder.setInitialFileName(root.getTitle());

			novelFile = folder.showSaveDialog(null);

			if (novelFile == null) {
				return novelFile;
			}
		}

		try {
			Document doc = DocumentBuilderFactory
							.newInstance()
							.newDocumentBuilder()
							.newDocument();
			tree2xml(doc, null, root);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(novelFile);

			transformer.transform(source, result);
			if (root.getTitle().equals(novelFile.getName())) {

			}
		} catch (ParserConfigurationException e) {
			throw new EditorIOException(e);
		} catch (TransformerException e) {
			throw new EditorIOException(e);
		}
		return novelFile;
	}

	private void tree2xml(Document doc,Element parent, TreeItem<String> item) {
		Element element = null;
		if (item instanceof Title) {
			element = doc.createElement("Novel");
			element.setAttribute("title", ((Title) item).getTitle());
			for (TreeItem<String> childItem : ((Section)item).getChildren()) {
				tree2xml(doc, element, childItem);
			}
			doc.appendChild(element);
			return;
		} else if (item instanceof Part) {
			element = doc.createElement("Part");
			element.setAttribute("title", ((Part) item).getTitle());
			element.appendChild(doc.createTextNode(((Part) item).getContext()));
		} else if (item instanceof Section) {
			element = doc.createElement("Section");
			element.setAttribute("title", ((Section) item).getTitle());
			for (TreeItem<String> childItem : ((Section)item).getChildren()) {
				tree2xml(doc, element, childItem);
			}
		}
		parent.appendChild(element);
	}

	public void export(IndexItem exportItem) {

		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Export Folder");
		File exportFolder = directoryChooser.showDialog(null);
		if (exportFolder == null) {
			return;
		}

		tree2text(exportItem, exportFolder);

	}

	private void tree2text(IndexItem item, File exportFolder) {
		if (item instanceof Part) {
			File exportFile = new File(exportFolder, ((Part) item).getTitle() + ".txt");
			PrintWriter pWriter = null;
			try {
				pWriter = new PrintWriter(new BufferedWriter(new FileWriter(exportFile)));
				pWriter.print(((Part) item).getContext());
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} finally {
				if (pWriter != null) {
					pWriter.close();
				}
			}
		} else if (item instanceof Section) {
			File newFolder = new File(exportFolder, ((Section) item).getTitle());
			newFolder.mkdir();
			for (TreeItem<String> child : item.getChildren()) {
				tree2text((IndexItem) child, newFolder);
			}
		} else {
			return;
		}

	}
		}
