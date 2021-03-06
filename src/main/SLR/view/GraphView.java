package main.SLR.view;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import main.GrammarAndGraphTransform.bean.Constant;
import main.GrammarAndGraphTransform.bean.Edge;
import main.GrammarAndGraphTransform.bean.Graph;
import main.GrammarAndGraphTransform.bean.Node;
import main.GrammarAndGraphTransform.enums.NodeType;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;

/**
 * @author dmrfcoder
 */
public class GraphView {

    SparseGraph<Node, Edge> localGraph;
    Transformer<Node, Paint> vertexPaint;
    Transformer<Edge, Stroke> edgeStrokeTransformer;



    public GraphView() {
        createTransformers();
    }

    public BasicVisualizationServer<Node, Edge> updateGraph(Graph graph) {
        createGraph(graph);
        return showGraph();

    }

    private void createTransformers() {
        this.vertexPaint = new Transformer<Node, Paint>() {
            @Override
            public Paint transform(Node node) {
                if (node.getNodeType() == NodeType.startNode) {
                    return Constant.startNodeColor;
                } else if (node.getNodeType() == NodeType.endNode) {
                    return Constant.endNodeColor;
                } else {
                    return Constant.normalNodeColor;
                }
            }
        };

        final Stroke edgeStroke = new BasicStroke();


        edgeStrokeTransformer = new Transformer<Edge, Stroke>() {
            @Override
            public Stroke transform(Edge edge) {
                return edgeStroke;
            }
        };

    }


    public BasicVisualizationServer<Node, Edge> showGraph() {

        // The Layout<V, E> is parameterized by the vertex and edge types
        Layout<Node, Edge> layout = new CircleLayout<Node, Edge>(localGraph);
        // sets the initial size of the space
        layout.setSize(new Dimension(600, 600));
        // The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<Node, Edge> vv =
                new BasicVisualizationServer<Node, Edge>(layout);
        // Sets the viewing area size
        vv.setPreferredSize(new Dimension(650, 650));
        // apply transformers
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);

        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);

        vv.getRenderContext().setVertexLabelTransformer(new Transformer<Node, String>() {
            @Override
            public String transform(Node node) {
                return node.getValue();
            }
        });

        vv.getRenderContext().setEdgeLabelTransformer(new Transformer<Edge, String>() {
            @Override
            public String transform(Edge edge) {
                return edge.getEdgeValue();
            }
        });
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        return vv;


    }

    private void createGraph(Graph graph) {


        localGraph = new SparseGraph<Node, Edge>();
        for (Node node : graph.getNodes()) {
            localGraph.addVertex(node);
        }

        for (Edge edge : graph.getEdges()) {
            localGraph.addEdge(edge, edge.getStartNode(),
                    edge.getEndNode(), EdgeType.DIRECTED);


        }

    }


}