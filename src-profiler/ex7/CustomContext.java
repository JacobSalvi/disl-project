package ex7;

import ch.usi.dag.disl.staticcontext.InstructionStaticContext;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;

import javax.xml.transform.Source;

public class CustomContext extends InstructionStaticContext{

    public record ArrayInfo(String desc, int dimensions){}

    public int getArgument(){
        InsnList list = staticContextData.getMethodNode().instructions;
        int index = this.getIndex();
        AbstractInsnNode node = list.get(index);
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
            case MultiANewArrayInsnNode n -> {
                return n.desc;
            }
            default -> {}
        }
        return "";
    }

    public int getDimension() {
        InsnList list = staticContextData.getMethodNode().instructions;
        int index = this.getIndex();
        AbstractInsnNode node = list.get(index);
        switch(node){
            case MultiANewArrayInsnNode n -> {
                return n.dims;
            }
            default -> {}
        }
        return 0;
    }

    public boolean isAnewArray() {
        InsnList list = staticContextData.getMethodNode().instructions;
        int index = this.getIndex();
        for (int i = 0; i < 4; i++) {
            AbstractInsnNode node = list.get(index);
            if(node.getOpcode() == 197) {
                return true;
            }
        }
        return false;
    }

    // this might seem redundant, but we couldn't use the dynamic context getStackValue because we got the out of bound (of the stack) error
    // and we need yo have 4 different functions since we cannot pass a parameter here.
    public int getDimension1() {
        InsnList list = staticContextData.getMethodNode().instructions;
        int index = this.getIndex();
        AbstractInsnNode node = list.get(index - 1);
        if (node.getOpcode() >= 3 && node.getOpcode() <= 8) {
            return node.getOpcode() - 3; // this instruction is a iconst_x, so we return the value of the constant
        } else if (node instanceof IntInsnNode && node.getOpcode() == 16) {
            return ((IntInsnNode) node).operand;  // if is a bipush we return the operand
        }
        return 0;
    }

    public int getDimension2() {
        InsnList list = staticContextData.getMethodNode().instructions;
        int index = this.getIndex();
        AbstractInsnNode node = list.get(index - 2);
        if (node.getOpcode() >= 3 && node.getOpcode() <= 8) {
            return node.getOpcode() - 3; // this instruction is a iconst_x, so we return the value of the constant
        } else if (node instanceof IntInsnNode && node.getOpcode() == 16) {
            return ((IntInsnNode) node).operand;  // if is a bipush we return the operand
        }
        return 0;
    }

    public int getDimension3() {
        InsnList list = staticContextData.getMethodNode().instructions;
        int index = this.getIndex();
        AbstractInsnNode node = list.get(index - 3);
        if (node.getOpcode() >= 3 && node.getOpcode() <= 8) {
            return node.getOpcode() - 3; // this instruction is a iconst_x, so we return the value of the constant
        } else if (node instanceof IntInsnNode && node.getOpcode() == 16) {
            return ((IntInsnNode) node).operand;  // if is a bipush we return the operand
        }
        return 0;
    }

    public int getDimension4() {
        InsnList list = staticContextData.getMethodNode().instructions;
        int index = this.getIndex();
        AbstractInsnNode node = list.get(index - 4);
        if (node.getOpcode() >= 3 && node.getOpcode() <= 8) {
            return node.getOpcode() - 3; // this instruction is a iconst_x, so we return the value of the constant
        } else if (node instanceof IntInsnNode && node.getOpcode() == 16) {
            return ((IntInsnNode) node).operand;  // if is a bipush we return the operand
        }
        return 0;
    }


}
