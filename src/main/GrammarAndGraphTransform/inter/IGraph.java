package main.GrammarAndGraphTransform.inter;


import main.GrammarAndGraphTransform.bean.Edge;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 * <p>
 * Graph的接口
 */
public interface IGraph {


    void addEdge(Edge edge);


    void addEdge(Object startNode, Object endNode, String edgeValue);

    String[][] getStrEdges();


    void clearGraph();


    void updateChuTai(String[] chuTai);

    void updateZhongTai(String[] zhongTai);


}
