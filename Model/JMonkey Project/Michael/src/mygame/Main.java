package mygame;

import com.bulletphysics.collision.shapes.TriangleShape;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.scene.VertexBuffer;
import jme3tools.navmesh.NavMesh;
import jme3tools.navmesh.util.NavMeshGenerator;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    boolean wireframe;
    Material mat;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {

        inputManager.addMapping("toggle wireframe", new KeyTrigger(KeyInput.KEY_T));
        inputManager.addListener(actionListener, "toggle wireframe");

        Spatial ship = assetManager.loadModel("Models/AccurateModelExp/AccurateModelExp.j3o");


        Node node;
        node = (Node) ship;
        Node chil1 = (Node) node.getChildren().get(2);
        Geometry chil = (Geometry) chil1.getChildren().get(0);

        
        
        Mesh shipMesh = chil.getMesh();
        NavMesh shipNM = new NavMesh();
        
        //NavMeshGenerator generator = new NavMeshGenerator();
        
        //Mesh optimisedMesh = generator.optimize(shipMesh);
        
        shipNM.loadFromMesh(shipMesh);


        for (int i = 0; i < shipNM.getNumCells(); i++) {
            TriangleShape tr;
            Vector3f v0 = shipNM.getCell(i).getVertex(0);
            Vector3f v1 = shipNM.getCell(i).getVertex(1);
            Vector3f v2 = shipNM.getCell(i).getVertex(2);

            Mesh lineMesh = new Mesh();
            lineMesh.setMode(Mesh.Mode.Lines);
            lineMesh.setBuffer(VertexBuffer.Type.Position, 3, new float[]{v0.x, v0.y, v0.z, v1.x, v1.y, v1.z});
            lineMesh.setBuffer(VertexBuffer.Type.Index, 2, new short[]{0, 1});
            lineMesh.updateBound();
            lineMesh.updateCounts();
            Geometry lineGeometry = new Geometry("line", lineMesh);
            Material lineMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            lineGeometry.setMaterial(lineMaterial);
            rootNode.attachChild(lineGeometry);
            
            lineMesh = new Mesh();
            lineMesh.setMode(Mesh.Mode.Lines);
            lineMesh.setBuffer(VertexBuffer.Type.Position, 3, new float[]{v0.x, v0.y, v0.z, v2.x, v2.y, v2.z});
            lineMesh.setBuffer(VertexBuffer.Type.Index, 2, new short[]{0, 1});
            lineMesh.updateBound();
            lineMesh.updateCounts();
            lineGeometry = new Geometry("line", lineMesh);
            lineMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            lineGeometry.setMaterial(lineMaterial);
            rootNode.attachChild(lineGeometry);
            
            lineMesh = new Mesh();
            lineMesh.setMode(Mesh.Mode.Lines);
            lineMesh.setBuffer(VertexBuffer.Type.Position, 3, new float[]{v2.x, v2.y, v2.z, v1.x, v1.y, v1.z});
            lineMesh.setBuffer(VertexBuffer.Type.Index, 2, new short[]{0, 1});
            lineMesh.updateBound();
            lineMesh.updateCounts();
            lineGeometry = new Geometry("line", lineMesh);
            lineMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            lineGeometry.setMaterial(lineMaterial);
            rootNode.attachChild(lineGeometry);
        }

        //System.out.println(chil1.getChildren().size());
        //mat = chil.getMaterial();
        //rootNode.attachChild(chil1);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean pressed, float tpf) {
            // toggle wireframe
            if (name.equals("toggle wireframe") && !pressed) {
                wireframe = !wireframe; // toggle boolean
                mat.getAdditionalRenderState().setWireframe(wireframe);
            }
            // else ... other input tests.
        }
    };
}
