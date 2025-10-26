import com.atur.*;
import org.junit.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MSTTests {
    @Test
    public void testSmallGraphMSTCostEqual() throws Exception {
        List<Graph> graphs = JsonIO.readGraphs("input.json");
        Graph g = graphs.get(0);
        Result p = PrimMST.run(g);
        Result k = KruskalMST.run(g);
        assertEquals(p.costSum, k.costSum, 1e-6);
        assertEquals(g.nodes - 1, p.mstEdges.size());
        assertEquals(g.nodes - 1, k.mstEdges.size());
    }
}
