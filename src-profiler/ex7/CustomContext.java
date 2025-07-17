package ex7;

import ch.usi.dag.disl.staticcontext.InstructionStaticContext;

import java.lang.classfile.CodeElement;
import java.lang.classfile.Instruction;
import java.lang.classfile.Opcode;
import java.lang.classfile.instruction.*;
import java.util.List;

public class CustomContext extends InstructionStaticContext{

    public record ArrayInfo(String desc, int dimensions){}

    public int getArgument(){
        List<CodeElement> list = staticContextData.getMethodModel().instructions();
        int index = this.getIndex();
        CodeElement node = list.get(index);
        switch(node){
            case ConstantInstruction.ArgumentConstantInstruction n -> {
                return n.constantValue();
            }
            case NewPrimitiveArrayInstruction n -> {
                return n.typeKind().newarrayCode();
            }
            default -> {}
        }
        return -1;
    }

    public String getType(){
        List<CodeElement> list = staticContextData.getMethodModel().instructions();
        int index = this.getIndex();
        CodeElement node = list.get(index);
        switch(node){
            case NewObjectInstruction n -> {
                return n.className().asSymbol().descriptorString();
            }
            case NewReferenceArrayInstruction n -> {
                return n.componentType().asSymbol().descriptorString();
            }
            case TypeCheckInstruction n -> {
                return n.type().asSymbol().descriptorString();
            }
            case NewMultiArrayInstruction n -> {
                return n.arrayType().asSymbol().descriptorString();
            }
            default -> {}
        }
        return "";
    }

    public int getDimension() {
        List<CodeElement> list = staticContextData.getMethodModel().instructions();
        int index = this.getIndex();
        CodeElement node = list.get(index);
        switch(node){
            case NewMultiArrayInstruction n -> {
                return n.dimensions();
            }
            default -> {}
        }
        return 0;
    }

    public boolean isAnewArray() {
        List<CodeElement> list = staticContextData.getMethodModel().instructions();
        int index = this.getIndex();
        for (int i = 0; i < 4; i++) {
            CodeElement node = list.get(index);
            if (node instanceof Instruction instruction) {
                return instruction.opcode() == Opcode.ANEWARRAY;
            }
        }
        return false;
    }

    // this might seem redundant, but we couldn't use the dynamic context getStackValue because we got the out of bound (of the stack) error
    // and we need yo have 4 different functions since we cannot pass a parameter here.
    public int getDimension1() {
        List<CodeElement> list = staticContextData.getMethodModel().instructions();
        int index = this.getIndex();
        CodeElement node = list.get(index - 1);
        if (node instanceof ConstantInstruction constantInstruction) {
            if (constantInstruction.constantValue() instanceof Integer integer) {
                return integer;
            }
        }
        return 0;
    }

    public int getDimension2() {
        List<CodeElement> list = staticContextData.getMethodModel().instructions();
        int index = this.getIndex();
        CodeElement node = list.get(index - 2);
        if (node instanceof ConstantInstruction constantInstruction) {
            if (constantInstruction.constantValue() instanceof Integer integer) {
                return integer;
            }
        }
        return 0;
    }

    public int getDimension3() {
        List<CodeElement> list = staticContextData.getMethodModel().instructions();
        int index = this.getIndex();
        CodeElement node = list.get(index - 3);
        if (node instanceof ConstantInstruction constantInstruction) {
            if (constantInstruction.constantValue() instanceof Integer integer) {
                return integer;
            }
        }
        return 0;
    }

    public int getDimension4() {
        List<CodeElement> list = staticContextData.getMethodModel().instructions();
        int index = this.getIndex();
        CodeElement node = list.get(index - 4);
        if (node instanceof ConstantInstruction constantInstruction) {
            if (constantInstruction.constantValue() instanceof Integer integer) {
                return integer;
            }
        }
        return 0;
    }


}
