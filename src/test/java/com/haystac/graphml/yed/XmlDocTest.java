package com.haystac.graphml.yed;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 * Tests {@link XmlDoc}.
 *
 * @author Adrian Wilke
 */
public class XmlDocTest {

    /**
     * Tests, if a XML document is created. Therefore, instances of
     * DocumentBuilderFactory, DocumentBuilder, and Document should be automatically
     * created.
     */
    @Test
    public void test() {
        XmlDoc xmlDoc = new XmlDoc();
        xmlDoc.createDocument();
        Document document = xmlDoc.getDocument();
        Assert.assertNotNull("XML document was created", document);
    }
}