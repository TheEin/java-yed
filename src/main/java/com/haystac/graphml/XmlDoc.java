package com.haystac.graphml;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * XML document.
 * <p>
 * To create an underlying {@link Document}, {@link #createDocument()} can be
 * used.
 * <p>
 * The required instances of {@link DocumentBuilderFactory},
 * {@link DocumentBuilder}, and {@link Document} can be modified of sub-classes
 * extending this class.
 */
public class XmlDoc {

    private DocumentBuilderFactory documentBuilderFactory;

    private DocumentBuilder documentBuilder;

    private Document document;

    /**
     * Creates default {@link DocumentBuilderFactory}.
     */
    protected DocumentBuilderFactory createDocumentBuilderFactory() {
        return DocumentBuilderFactory.newInstance();
    }

    /**
     * Creates default {@link DocumentBuilder}.
     */
    protected DocumentBuilder createDocumentBuilder() {
        if (documentBuilderFactory == null) {
            documentBuilderFactory = createDocumentBuilderFactory();
        }
        try {
            return documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates default {@link Document}.
     */
    public Document createDocument() {
        if (documentBuilder == null) {
            documentBuilder = createDocumentBuilder();
        }
        return documentBuilder.newDocument();
    }

    /**
     * Gets the XML {@link Document}.
     */
    public Document getDocument() {
        if (document == null) {
            document = createDocument();
        }
        return document;
    }
}