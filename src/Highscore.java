import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Highscore {
    private final List<Score> scores = new ArrayList<>();

    public void addScore(final Score score) {
        scores.add(score);
    }

    public void writeToXMLFile(final String location) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        Element root = doc.createElement("Scores");
        doc.appendChild(root);

        for(Score score : scores) {
            Element scoreElement = doc.createElement("score");
            scoreElement.setAttribute("name", score.getName());
            scoreElement.setAttribute("score", Integer.toString(score.getScore()));
            root.appendChild(scoreElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(location));

        transformer.transform(source, result);
    }

    public void loadFromXMLFile(final String location) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();

        Document doc = dbBuilder.parse(new File(location));
        doc.getDocumentElement().normalize();

        scores.clear();

        NodeList nodeList = doc.getElementsByTagName("score");

        for(int i = 0; i < nodeList.getLength(); ++i) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                String name = nodeList.item(i).getAttributes().getNamedItem("name").getNodeValue();
                int score = Integer.parseInt(nodeList.item(i).getAttributes().getNamedItem("score").getNodeValue());
                addScore(new Score(name, score));
            }
        }
    }

    public List<Score> getScores() {
        return scores;
    }
}