package com.haystac.graphml.yed;

import com.haystac.graphml.Color;
import com.haystac.graphml.Node;

import java.io.File;

/**
 * Examples.
 *
 */
public class Examples {

    public static void main(String[] args) throws Exception {
        Examples examples = new Examples();
        examples.exampleA();
        examples.exampleB();
    }

    public void exampleA() throws Exception {

        // Set file to write
        File file = new File("exampleA.yEd.graphml");
        System.out.println("Example file: " + file.getAbsolutePath());

        // Create document
        YedDoc yedDoc = new YedDoc().initialize();

        // Create nodes
        String a = yedDoc.addNode("Adrian");
        String b = yedDoc.addNode("Benjamin");
        String c = yedDoc.addNode("Caesar");

        // Create edges
        yedDoc.createEdge(a, b);
        yedDoc.createEdge(b, c);
        yedDoc.createEdge(c, a);

        // Write file
        Io.write(yedDoc.getDocument(), file);
    }

    public void exampleB() throws Exception {

        // Set file to write
        File file = new File("exampleB.yEd.graphml");
        System.out.println("Example file: " + file.getAbsolutePath());

        // Create and configure a document
        YedDoc yedDoc = new YedDoc().initialize();
        yedDoc.setFontFamily("Roboto");
        yedDoc.setFontSize(14);
        yedDoc.setFontStyle("bold");

        // Create nodes of different types
        YedNode<?> hero = new ShapeNode().color(Color.ORANGE);
        String s = yedDoc.add(hero.label("Superman"));
        String b = yedDoc.add(hero.label("Batman"));
        String h = yedDoc.add(hero.label("Harley Quinn"));
        YedNode<?> robot = new ShapeNode().color(Color.YELLOW);
        String r = yedDoc.add(robot.label("Robocob"));
        String t = yedDoc.add(robot.label("T-1000"));

        // Create edges of different types
        int knows = 0;
        String knowsLabel = "knows";
        yedDoc.createEdge(s, b, knowsLabel, knows);
        yedDoc.createEdge(t, s, knowsLabel, knows);
        int loves = 1;
        String lovesLabel = "loves";
        yedDoc.createEdge(s, h, lovesLabel, loves);
        yedDoc.createEdge(b, h, lovesLabel, loves);
        yedDoc.createEdge(h, t, lovesLabel, loves);
        Integer unknown = null;
        String unknownLabel = null;
        yedDoc.createEdge(r, h, unknownLabel, unknown);

        // Write file
        Io.write(yedDoc.getDocument(), file);
    }
}