package main.GrammarAndGraphTransform.convert;

import main.GrammarAndGraphTransform.base.BaseConvertGrammarToGraph;
import main.GrammarAndGraphTransform.bean.*;
import main.GrammarAndGraphTransform.enums.ModelType;
import main.GrammarAndGraphTransform.enums.NodeType;
import main.GrammarAndGraphTransform.exception.GrammarPhaseException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public class GrammarToGraph extends BaseConvertGrammarToGraph {


    @Override
    public Graph GrammarToGraph(Grammar leftGrammar) {
        return null;
    }


    @Override
    public void addEdges(String node, String expression, ModelType modelType) throws GrammarPhaseException {
        String[] split = expression.split("\\|");

        switch (modelType) {
            case LeftToNf:
                try {
                    leftGrammarAddEdges(node, split);
                } catch (GrammarPhaseException e) {
                    throw e;
                }

                break;
            case RightToNf:
                rightGrammarAddEdges(node, split);
            default:
                break;

        }


    }


    /**
     * 将左正规文法对应的表达式解析为若干条边
     * <p>
     * 通过操作leftGraph来添加边
     *
     * @param endNode
     * @param split
     */
    private void leftGrammarAddEdges(String endNode, String[] split) throws GrammarPhaseException {

        leftGraph.addEdge(new Node("S0", NodeType.startNode), new Node("S1"), "E");

//        List<Edge> edges = new ArrayList<>();
//
//        for (String item : split) {
//            if (item.length() == 1) {
//                char itemZero = item.charAt(0);
//                if (isInt(itemZero)) {
//
//                    leftGraph.addEdge(new Node(Constant.startNodeName, NodeType.startNode), endNode, String.valueOf(itemZero));
//                } else {
//
//                    throw new GrammarPhaseException(item + "格式非法！");
//                }
//
//
//            } else if (item.length() == 2) {
//                char itemZero = item.charAt(0);
//                char itemFirst = item.charAt(1);
//
//                if (isChar(itemZero) && isInt(itemFirst)) {
//                    leftGraph.addEdge(String.valueOf(itemZero), endNode, String.valueOf(itemFirst));
//                } else {
//                    throw new GrammarPhaseException(item + "不是合法的左文法表达式！");
//
//                }
//
//
//            }
//        }


    }


    /**
     * 将右正规文法对应的表达式解析为若干条边
     * 通过操作rightGraph来添加边
     *
     * @param node
     * @param split example: 某产生式为  D-> 0A|0B|0C|0
     *              node  = D
     *              split = {"0","B0","C0","0"}
     */
    private void rightGrammarAddEdges(String node, String[] split) throws GrammarPhaseException {
        Node endNode = new Node(Constant.endNodeName, NodeType.endNode);

        for (String item : split) {
            if (item.length() == 1)  {
                char itemZero = item.charAt(0);
                if (isInt(itemZero)) {

                    rightGraph.addEdge(new Node(Constant.startNodeName, NodeType.startNode), endNode, String.valueOf(itemZero));
                } else {

                    throw new GrammarPhaseException(item + "格式非法！");
                }


            } else if (item.length() == 2) {
                char itemZero = item.charAt(0);
                char itemFirst = item.charAt(1);

                if (isChar(itemFirst) && isInt(itemZero)) {

                    rightGraph.addEdge(node, String.valueOf(itemFirst), String.valueOf(itemZero));
                } else {
                    throw new GrammarPhaseException(item + "不是合法的右文法表达式！");

                }


            }
        }


    }


    //产生式：start[i] -> expr[i]
    //VT = {0,1,....}
    //VN = {A,B,C,D,...}
    //输入：两个字符数组
    //开始符号默认是start[0]
    //终结符默认 "F"
    public void rightGrammarToGraph(String[] start, String[] expr) throws GrammarPhaseException {
        GrammarToGraph grammarToGraph = new GrammarToGraph();
        for (int i = 0; i < start.length; i++) {
            grammarToGraph.addEdges(start[i], expr[i], ModelType.RightToNf);
        }

        String[][] edges = grammarToGraph.getStrEdges(ModelType.RightToNf);

        for (String[] edge : edges) {
            System.out.println(edge[0] + "-" + edge[1] + "->" + edge[2]);
        }
    }

}
