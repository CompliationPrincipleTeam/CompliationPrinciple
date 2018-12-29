package main.SLR.view;

import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import main.GrammarAndGraphTransform.bean.*;
import main.GrammarAndGraphTransform.convert.GrammarToGraph;
import main.GrammarAndGraphTransform.convert.GraphToGrammar;
import main.GrammarAndGraphTransform.enums.ModelType;
import main.GrammarAndGraphTransform.enums.NodeType;
import main.GrammarAndGraphTransform.exception.GrammarPhaseException;
import main.GrammarAndGraphTransform.inter.IMainView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public class MainView extends JFrame {


    private GraphView graphPoetView;


    private JTable jTableGraph;


    private BasicVisualizationServer<Node, Edge> nodeEdgeBasicVisualizationServer;


    public MainView() throws HeadlessException {
        super();


        try {
            UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        setIconImage(new ImageIcon("res/icon.png").getImage());


        initView();
        graphPoetView = new GraphView();

        setVisible(true);

        Node node0=new Node("S0",NodeType.normalNode);
        Node node1=new Node("S1",NodeType.normalNode);
        Node node2=new Node("S2",NodeType.normalNode);
        Node node3=new Node("S3",NodeType.normalNode);
        Node node4=new Node("S4",NodeType.normalNode);
        Node node5=new Node("S5",NodeType.normalNode);
        Node node6=new Node("S6",NodeType.normalNode);
        Node node7=new Node("S7",NodeType.normalNode);
        Node node8=new Node("S8",NodeType.normalNode);
        Node node9=new Node("S9",NodeType.normalNode);
        Node node10=new Node("S10",NodeType.normalNode);
        Node node11=new Node("S11",NodeType.normalNode);
        Node node12=new Node("S12",NodeType.normalNode);


        Graph demograph = new Graph();
        demograph.addEdge(new Edge(node0,node1, "E"));
        demograph.addEdge(new Edge(node0, node2, "T"));
        demograph.addEdge(new Edge(node0, node3, "F"));
        demograph.addEdge(new Edge(node0,node4, "("));
        demograph.addEdge(new Edge(node0,node5, "i"));

        demograph.addEdge(new Edge(node1, node6, "+"));
        demograph.addEdge(new Edge(node1, node12, "#S'->E"));

        demograph.addEdge(new Edge(node2, node7, "*"));
        demograph.addEdge(new Edge(node2, node12, "#E->T."));

        demograph.addEdge(new Edge(node3, node12, "#T->F"));


        demograph.addEdge(new Edge(node4,node8, "E"));
        demograph.addEdge(new Edge(node4,node2, "T"));
        demograph.addEdge(new Edge(node4, node3, "F"));
        demograph.addEdge(new Edge(node4, node4, "("));
        demograph.addEdge(new Edge(node4, node5, "i"));

        demograph.addEdge(new Edge(node5, node12, "#F->i"));

        demograph.addEdge(new Edge(node6, node9, "T"));
        demograph.addEdge(new Edge(node6,node3, "F"));
        demograph.addEdge(new Edge(node6, node4, "("));
        demograph.addEdge(new Edge(node6,node5, "i"));


        demograph.addEdge(new Edge(node7, node10, "F"));
        demograph.addEdge(new Edge(node7, node4, "("));
        demograph.addEdge(new Edge(node7, node5, "i"));

        demograph.addEdge(new Edge(node8, node11, ")"));
        demograph.addEdge(new Edge(node8, node6, "+"));

        demograph.addEdge(new Edge(node9, node12, "#E->T*F"));
        demograph.addEdge(new Edge(node9,node7, "*"));

        demograph.addEdge(new Edge(node10,node12, "#F->(E)"));



//        demograph.addNode(new Node("S0"));
//        demograph.addNode(new Node("S1"));
//        demograph.addNode(new Node("S2"));
//        demograph.addNode(new Node("S3"));
//        demograph.addNode(new Node("S4"));
//        demograph.addNode(new Node("S5"));
//        demograph.addNode(new Node("S6"));
//        demograph.addNode(new Node("S7"));
//        demograph.addNode(new Node("S8"));
//        demograph.addNode(new Node("S9"));
//        demograph.addNode(new Node("S10"));
//        demograph.addNode(new Node("S11"));
//        demograph.addNode(new Node("S12"));




        nodeEdgeBasicVisualizationServer = graphPoetView.updateGraph(demograph);
        drawGraph();
        repaint();
    }


    public void initView() {


        setTitle("词法分析");
        setSize(Constant.GraphWidth, Constant.GraphHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frm_Height = this.getHeight();
        int frm_width = this.getWidth();
        setLocation((screenWidth - frm_width) / 2,
                (screenHeight - frm_Height) / 2);


        getContentPane().setLayout(null);


        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);


    }


    private void drawGraph() {


        graphPoetView = new GraphView();


        nodeEdgeBasicVisualizationServer.setBounds(0, 0, 650, 650);
        add(nodeEdgeBasicVisualizationServer);
    }


}
