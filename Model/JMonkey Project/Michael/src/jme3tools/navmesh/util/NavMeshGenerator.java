package jme3tools.navmesh.util;

import com.jme3.math.FastMath;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.mesh.IndexBuffer;
import com.jme3.terrain.Terrain;
import java.nio.FloatBuffer;
import java.util.Random;
import org.critterai.nmgen.NavmeshGenerator;
import org.critterai.nmgen.TriangleMesh;

public class NavMeshGenerator {

    private org.critterai.nmgen.NavmeshGenerator nmgen;
    //these work for the room thingie
    private float cellSize = 0.05f;
    private float cellHeight = 0.1f;
    private float minTraversableHeight = 0.2f;
    private float maxTraversableStep = 0.1f;
    private float maxTraversableSlope = 30f;
    private boolean clipLedges = false;
    private float traversableAreaBorderSize = 0.01f;
    private int smoothingThreshold = 2;
    private boolean useConservativeExpansion = true;
    private int minUnconnectedRegionSize = 1;
    private int mergeRegionSize = 1;
    private float maxEdgeLength = 0;
    private float edgeMaxDeviation = 0.1f;
    private int maxVertsPerPoly = 3;
    private float contourSampleDistance = 0f;
    private float contourMaxDeviation = 0f;
    private float cellSizeArray[] = {0.5f, 0.01f, 0.005f, 0.001f};
    private float cellHeightArray[] = {0.2f, 0.1f, 0.05f, 0.01f};
    private boolean clipLedgesArray[] = {true, false};
    private float traversableAreaBorderSizeArray[] = {0f, 1f, 0.06f, 0.05f, 0,01f, 0.006f, 0.002f};
    private int smoothingThresholdArray[] = {0, 1, 2, 4};
    private boolean useConservativeExpansionArray[] = {false, true};
    private int minUnconnectedRegionSizeArray[] = {1, 3, 9, 13, 30, 60, 2000};
    private int mergeRegionSizeArray[] = {0, 1, 3, 9, 50, 200};
    private float edgeMaxDeviationArray[] = {0f, 5f, 1f, 0.2f, 0.05f, 0.01f};
    private int maxVertsPerPolyArray[] = {3, 6};
    private float contourSampleDistanceArray[] = {0f, 0.95f, 1f, 1.2f, 1.5f, 3.f, 6f, 10f};
    private float contourMaxDeviationArray[] = {0f, 0.5f, 0.95f, 1f, 1.2f, 1.5f, 3.f, 6f, 10f};
    
    public NavMeshGenerator() {
    }

    public void printParams() {
        System.out.println("Cell Size: " + cellSize);
        System.out.println("Cell Height: " + cellHeight);
        System.out.println("Min Trav. Height: " + minTraversableHeight);
        System.out.println("Max Trav. Step: " + maxTraversableStep);
        System.out.println("Max Trav. Slope: " + maxTraversableSlope);
        System.out.println("Clip Ledges: " + clipLedges);
        System.out.println("Trav. Area Border Size: " + traversableAreaBorderSize);
        System.out.println("Smooth Thresh.: " + smoothingThreshold);
        System.out.println("Use Cons. Expansion: " + useConservativeExpansion);
        System.out.println("Min Unconn. Region Size: " + minUnconnectedRegionSize);
        System.out.println("Merge Region Size: " + mergeRegionSize);
        System.out.println("Max Edge Length: " + maxEdgeLength);
        System.out.println("Edge Max Dev.: " + edgeMaxDeviation);
        System.out.println("Max Verts/Poly: " + maxVertsPerPoly);
        System.out.println("Contour Sample Dist: " + contourSampleDistance);
        System.out.println("Contour Max Dev.: " + contourMaxDeviation);
    }

    public Mesh optimize(Mesh mesh) {
        System.out.println("starting\n"+mesh.getTriangleCount()+" triangles");
        FloatBuffer pb;
        IndexBuffer ib;
        float[] positions;
        int[] indices;
        TriangleMesh triMesh = null;


        int found = 0;
        int foundT = 0;
        int tried = 0;

        Random r = new Random();

        while (true) {
            tried++;
            cellSize = cellSizeArray[r.nextInt(cellSizeArray.length)];
            cellHeight = cellHeightArray[r.nextInt(cellHeightArray.length)];
            minTraversableHeight = cellHeight * (r.nextInt(9) + 1);
            maxTraversableStep = cellSize * (r.nextInt(4) + 2);
            clipLedges = clipLedgesArray[r.nextInt(clipLedgesArray.length)];
            traversableAreaBorderSize = traversableAreaBorderSizeArray[r.nextInt(traversableAreaBorderSizeArray.length)];
            smoothingThreshold = smoothingThresholdArray[r.nextInt(smoothingThresholdArray.length)];
            useConservativeExpansion = useConservativeExpansionArray[r.nextInt(useConservativeExpansionArray.length)];
            minUnconnectedRegionSize = minUnconnectedRegionSizeArray[r.nextInt(minUnconnectedRegionSizeArray.length)];
            mergeRegionSize = mergeRegionSizeArray[r.nextInt(mergeRegionSizeArray.length)];
            edgeMaxDeviation = edgeMaxDeviationArray[r.nextInt(edgeMaxDeviationArray.length)];
            maxVertsPerPoly = maxVertsPerPolyArray[r.nextInt(maxVertsPerPolyArray.length)];
            contourSampleDistance = contourSampleDistanceArray[r.nextInt(contourSampleDistanceArray.length)];
            contourMaxDeviation = contourMaxDeviationArray[r.nextInt(contourMaxDeviationArray.length)] * cellSize;

            nmgen = new NavmeshGenerator(cellSize, cellHeight, minTraversableHeight,
                    maxTraversableStep, maxTraversableSlope,
                    clipLedges, traversableAreaBorderSize,
                    smoothingThreshold, useConservativeExpansion,
                    minUnconnectedRegionSize, mergeRegionSize,
                    maxEdgeLength, edgeMaxDeviation, maxVertsPerPoly,
                    contourSampleDistance, contourMaxDeviation);

            pb = mesh.getFloatBuffer(Type.Position);
            ib = mesh.getIndexBuffer();

            // copy positions to float array
            positions = new float[pb.capacity()];
            pb.clear();
            pb.get(positions);

            // generate int array of indices
            indices = new int[ib.size()];
            for (int i = 0; i < indices.length; i++) {
                indices[i] = ib.get(i);
            }

            triMesh = nmgen.build(positions, indices, null, null);

            if (triMesh != null && triMesh.triangleRegions.length > 10) {
                found++;
                if (triMesh.triangleRegions.length > 300) {
                    foundT++;
                }
                System.out.println("************\n\n");
                System.out.println(foundT + "/" + found + "/" + tried);
                System.out.println(triMesh.triangleRegions.length + "triangles");
                printParams();
                System.out.println("\n\n************");

                if (foundT > 20000) {
                    break;
                }
            }


        }

        if (triMesh == null) {
            return null;
        }

        int[] indices2 = triMesh.indices;
        float[] positions2 = triMesh.vertices;

        Mesh mesh2 = new Mesh();
        mesh2.setBuffer(Type.Position, 3, positions2);
        mesh2.setBuffer(Type.Index, 3, indices2);
        mesh2.updateBound();
        mesh2.updateCounts();

        return mesh2;
    }

    public Mesh optimize(Terrain terr) {
        float[] floats = terr.getHeightMap();
        int length = floats.length;
        float size = (int) FastMath.sqrt(floats.length) * 3;
        float[] vertices = new float[length * 3];
        int[] indices = new int[length * 3];

        //TODO: indices are wrong
        for (int i = 0; i < length * 3; i += 3) {
            float xPos = (float) Math.IEEEremainder(i, size);
            float yPos = floats[i / 3];
            float zPos = i / (int) size;
            vertices[i] = xPos;
            vertices[i + 1] = yPos;
            vertices[i + 2] = yPos;
            indices[i] = i;
            indices[i + 1] = i + 1;
            indices[i + 2] = i + 2;
        }
        Mesh mesh2 = new Mesh();
        mesh2.setBuffer(Type.Position, 3, vertices);
        mesh2.setBuffer(Type.Index, 3, indices);
        mesh2.updateBound();
        mesh2.updateCounts();
        return mesh2;
    }

    /**
     * @return The height resolution used when sampling the source mesh. Value
     * must be > 0.
     */
    public float getCellHeight() {
        return cellHeight;
    }

    /**
     * @param cellHeight - The height resolution used when sampling the source
     * mesh. Value must be > 0.
     */
    public void setCellHeight(float cellHeight) {
        this.cellHeight = cellHeight;
    }

    /**
     * @return The width and depth resolution used when sampling the the source
     * mesh.
     */
    public float getCellSize() {
        return cellSize;
    }

    /**
     * @param cellSize - The width and depth resolution used when sampling the
     * the source mesh.
     */
    public void setCellSize(float cellSize) {
        this.cellSize = cellSize;
    }

    public boolean isClipLedges() {
        return clipLedges;
    }

    public void setClipLedges(boolean clipLedges) {
        this.clipLedges = clipLedges;
    }

    public float getContourMaxDeviation() {
        return contourMaxDeviation;
    }

    public void setContourMaxDeviation(float contourMaxDeviation) {
        this.contourMaxDeviation = contourMaxDeviation;
    }

    public float getContourSampleDistance() {
        return contourSampleDistance;
    }

    public void setContourSampleDistance(float contourSampleDistance) {
        this.contourSampleDistance = contourSampleDistance;
    }

    public float getEdgeMaxDeviation() {
        return edgeMaxDeviation;
    }

    public void setEdgeMaxDeviation(float edgeMaxDeviation) {
        this.edgeMaxDeviation = edgeMaxDeviation;
    }

    public float getMaxEdgeLength() {
        return maxEdgeLength;
    }

    public void setMaxEdgeLength(float maxEdgeLength) {
        this.maxEdgeLength = maxEdgeLength;
    }

    public float getMaxTraversableSlope() {
        return maxTraversableSlope;
    }

    public void setMaxTraversableSlope(float maxTraversableSlope) {
        this.maxTraversableSlope = maxTraversableSlope;
    }

    public float getMaxTraversableStep() {
        return maxTraversableStep;
    }

    public void setMaxTraversableStep(float maxTraversableStep) {
        this.maxTraversableStep = maxTraversableStep;
    }

    public int getMaxVertsPerPoly() {
        return maxVertsPerPoly;
    }

    public void setMaxVertsPerPoly(int maxVertsPerPoly) {
        this.maxVertsPerPoly = maxVertsPerPoly;
    }

    public int getMergeRegionSize() {
        return mergeRegionSize;
    }

    public void setMergeRegionSize(int mergeRegionSize) {
        this.mergeRegionSize = mergeRegionSize;
    }

    public float getMinTraversableHeight() {
        return minTraversableHeight;
    }

    public void setMinTraversableHeight(float minTraversableHeight) {
        this.minTraversableHeight = minTraversableHeight;
    }

    public int getMinUnconnectedRegionSize() {
        return minUnconnectedRegionSize;
    }

    public void setMinUnconnectedRegionSize(int minUnconnectedRegionSize) {
        this.minUnconnectedRegionSize = minUnconnectedRegionSize;
    }

    public NavmeshGenerator getNmgen() {
        return nmgen;
    }

    public void setNmgen(NavmeshGenerator nmgen) {
        this.nmgen = nmgen;
    }

    public int getSmoothingThreshold() {
        return smoothingThreshold;
    }

    public void setSmoothingThreshold(int smoothingThreshold) {
        this.smoothingThreshold = smoothingThreshold;
    }

    public float getTraversableAreaBorderSize() {
        return traversableAreaBorderSize;
    }

    public void setTraversableAreaBorderSize(float traversableAreaBorderSize) {
        this.traversableAreaBorderSize = traversableAreaBorderSize;
    }

    public boolean isUseConservativeExpansion() {
        return useConservativeExpansion;
    }

    public void setUseConservativeExpansion(boolean useConservativeExpansion) {
        this.useConservativeExpansion = useConservativeExpansion;
    }
}
