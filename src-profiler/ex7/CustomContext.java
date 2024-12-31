package ex7;

import ch.usi.dag.disl.staticcontext.InstructionStaticContext;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.IntInsnNode;

public class CustomContext extends InstructionStaticContext{

    public int getArgument(){
        InsnList list = staticContextData.getMethodNode().instructions;
        int index = this.getIndex();
        AbstractInsnNode node = list.get(index);
        int opcode = node.getOpcod();
        System.out.println("Check here: " + opcode + " class: " + node.getClass());
        switch(node){
            case IntInsnNode n -> {
                return n.operand;
            }
            default -> {}
        }
        return -1;
    }

    public String getType(){
        InsnList list = staticContextData.getMethodNode().instructions;
        int index = this.getIndex();
        AbstractInsnNode node = list.get(index);
        switch(node){
            case TypeInsnNode n -> {
                return n.desc;
            }
            default -> {}
        }
        return "";
    }

}
