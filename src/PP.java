// PP.java

import java.util.*;
import java.lang.*;

/*********/
/* Types */
/*********/

abstract class Type {}//Type

class Int extends Type {

    public String toString() {return "int";}

}//Int

class Bool extends Type {
    public String toString() {return "bool";}
}//Bool

class Array extends Type {

    Type elements;

    Array (Type elements) {
        this.elements = elements;
    }//Array

    public String toString() {return ("arrayOf" + elements.toString());}

}//Array

/**************************************/
/* Arithmetic and boolean expressions */
/**************************************/

abstract class PPExpr {
    abstract UPPExpr toUPP(ArrayList<String> locals);
}//PPExpr

class PPCte extends PPExpr {

    int val;

    PPCte (int val) {
        this.val = val;
    }//PPCte

    UPPExpr toUPP(ArrayList<String> locals){
        return new UPPCte(val);
    }

    public String toString() {return Integer.toString(val);}

}//PPCte

class PPTrue extends PPExpr {

    UPPTrue toUPP(ArrayList<String> locals){
        return new UPPTrue();
    }
    public String toString() {return "true";}

}//PPTrue

class PPFalse extends PPExpr {
    UPPFalse toUPP(ArrayList<String> locals){
        return new UPPFalse();
    }
    public String toString() {return "false";}
}//PPFalse

class PPVar extends PPExpr {

    String name;

    PPVar (String name) {
        this.name = name;
    }//PPVar

    UPPExpr toUPP(ArrayList<String> locals){
        if (locals.contains(name)){
            return new UPPVar(name);
        } else {
            return new UPPGVar(name);
        }
    }

    public String toString() {return name;}

}//PPVar

abstract class PPUnOp extends PPExpr {

    PPExpr e;

    abstract UPPExpr toUPP(ArrayList<String> locals);

}//PPUnOp

class PPInv extends PPUnOp {

    PPInv (PPExpr e) {
        this.e = e;
    }//PPInv

    UPPExpr toUPP(ArrayList<String> locals){
        return new UPPSub(new UPPCte(0), e.toUPP(locals));}

        public String toString() {return "- "+e.toString();}

}//PPInv

class PPNot extends PPUnOp {

    PPNot (PPExpr e) {
        this.e = e;
    }//PPNot

    UPPNot toUPP(ArrayList<String> locals){
        return new UPPNot(e.toUPP(locals));
    }

    public String toString() {return "not " + e.toString();}

}//PPNot

abstract class PPBinOp extends PPExpr {

    PPExpr e1, e2;

}//PPBinOp

class PPAdd extends PPBinOp {

    PPAdd (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPAdd

    UPPAdd toUPP(ArrayList<String> locals){
        return new UPPAdd(e1.toUPP(locals),e2.toUPP(locals));
    }

    public String toString() {return e1.toString() + " + " + e2.toString();}
}//PPAd

class PPSub extends PPBinOp {

    PPSub (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPSub

    UPPSub toUPP(ArrayList<String> locals){ 
        return new UPPSub(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " - " + e2.toString();}
}//PPSub

class PPMul extends PPBinOp {

    PPMul (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPMul

    UPPMul toUPP(ArrayList<String> locals){ return new UPPMul(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " x " + e2.toString();}

}//PPMul

class PPDiv extends PPBinOp {

    PPDiv (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPDiv

    UPPDiv toUPP(ArrayList<String> locals){ return new UPPDiv(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " / " + e2.toString();}

}//PPDiv

class PPAnd extends PPBinOp {

    PPAnd (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPAnd

    UPPAnd toUPP(ArrayList<String> locals){ return new UPPAnd(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " and " + e2.toString();}

}//PPAnd

class PPOr extends PPBinOp {

    PPOr (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPOr
    UPPOr toUPP(ArrayList<String> locals){ return new UPPOr(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " or" + e2.toString();}
}//PPOr

class PPLe extends PPBinOp {

    PPLe (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPLe
    UPPLe toUPP(ArrayList<String> locals){ return new UPPLe(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " < " + e2.toString();}

}//PPLe

class PPLeq extends PPBinOp {

    PPLeq (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPLeq
    UPPLeq toUPP(ArrayList<String> locals){ return new UPPLeq(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " <= " + e2.toString();}

}//PPLeq

class PPEq extends PPBinOp {

    PPEq (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPEq
    UPPEq toUPP(ArrayList<String> locals){ return new UPPEq(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " == " + e2.toString();}

}//PPEq

class PPNeq extends PPBinOp {

    PPNeq (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPNeq
    UPPNeq toUPP(ArrayList<String> locals){ return new UPPNeq(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " != " + e2.toString();}

}//PPNeq

class PPGeq extends PPBinOp {

    PPGeq (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPGeq
    UPPGeq toUPP(ArrayList<String> locals){ return new UPPGeq(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " >= " + e2.toString();}

}//PPGeq

class PPGe extends PPBinOp {

    PPGe (PPExpr e1, PPExpr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }//PPGe
    UPPGe toUPP(ArrayList<String> locals){ return new UPPGe(e1.toUPP(locals),e2.toUPP(locals));}

    public String toString() {return e1.toString() + " > " + e2.toString();}

}//PPGe

abstract class Callee {}//Callee

class Read extends Callee {
    public String toString() {return "read";}
}//Read

class Write extends Callee {
    public String toString() {return "write";}
}//Write

class User extends Callee {

    String name;

    User (String name) {
        this.name = name;
    }//User

    public String toString() {return name;}

}//User

class PPFunCall extends PPExpr {

    Callee callee;
    ArrayList<PPExpr> args;

    PPFunCall (Callee callee, ArrayList<PPExpr> args) {
        this.callee = callee;
        this.args = args;
    }//FunCall

    UPPFunCall toUPP(ArrayList<String> locals) {
        ArrayList<UPPExpr> arguments = new ArrayList<>();
        for (PPExpr arg : this.args) {
            arguments.add(arg.toUPP(locals));
        }
        return new UPPFunCall(callee, arguments);
    }

    public String toString() {
        String toString ="";
        toString = callee.toString() + "( ";
        for (PPExpr arg : args) {
            toString += arg.toString() + ",";
        }
        toString += " )";
        return toString;
    }

}//FunCall

class PPArrayGet extends PPExpr {

    PPExpr arr, index;

    PPArrayGet (PPExpr arr, PPExpr index) {
        this.arr = arr;
        this.index = index;
    }//PPArrayGet

    UPPExpr toUPP(ArrayList<String> locals){
        UPPExpr uPPindex = new UPPMul(new UPPCte(4), index.toUPP(locals));
        UPPExpr adress = new UPPAdd(arr.toUPP(locals),uPPindex);
        return new UPPLoad(adress);
    }

    public String toString() {return arr.toString() + "[" + index.toString() + "]" ;}

}//PPArrayGet

class PPArrayAlloc extends PPExpr {

    Type type;
    PPExpr size;

    PPArrayAlloc (Type type, PPExpr size) {
        this.type = type;
        this.size = size;
    }//PPArrayAlloc

    UPPExpr toUPP(ArrayList<String> locals){
        UPPExpr adress = new UPPMul(new UPPCte(4), size.toUPP(locals));
        ArrayList<UPPExpr> arguments = new ArrayList<UPPExpr>();
        arguments.add(adress);
        return new UPPFunCall(new Alloc(), arguments);
    }

    public String toString() {return "new arrayOf"+type.toString()+"["+size.toString()+"]";}

}//PPArrayAlloc

/****************/
/* Instructions */
/****************/

abstract class PPInst {
    abstract UPPInst toUPP(ArrayList<String> locals);
}//PPInst

class PPAssign extends PPInst {

    String name;
    PPExpr val;

    PPAssign (String name, PPExpr val) {
        this.name = name;
        this.val = val;
    }//PPAssign

    UPPAssign toUPP(ArrayList<String> locals){
        return new UPPAssign(name, val.toUPP(locals));
    }

    public String toString() {return name + "=" + val.toString();}

}//PPAssign

class PPArraySet extends PPInst {

    PPExpr arr, index, val;

    PPArraySet (PPExpr arr, PPExpr index, PPExpr val) {
        this.arr = arr;
        this.index = index;
        this.val = val;
    }//PPArraySet

    UPPStore toUPP(ArrayList<String> locals){
        UPPExpr address = new UPPAdd(arr.toUPP(locals),
            new UPPMul(new UPPCte(4),
                index.toUPP(locals))
            );
        UPPExpr uPPValue = val.toUPP(locals);
        return new UPPStore(address, uPPValue);
    }

    public String toString() {return arr.toString()+"["+index.toString()+"]"+"="+val.toString();}

}//PPArraySet

class PPCond extends PPInst {

    PPExpr cond;
    PPInst i1, i2;

    PPCond (PPExpr cond, PPInst i1, PPInst i2) {
        this.cond = cond;
        this.i1 = i1;
        this.i2 = i2;
    }//PPCond

    UPPCond toUPP(ArrayList<String> locals) {return new UPPCond(cond.toUPP(locals), i1.toUPP(locals), i2.toUPP(locals));}

    public String toString() {return "if ("+cond.toString()+") then "+i1.toString()+" else "+i2.toString();}

}//PPCond

class PPWhile extends PPInst {

    PPExpr cond;
    PPInst i;

    PPWhile (PPExpr cond, PPInst i) {
        this.cond = cond;
        this.i = i;
    }//PPWhile

    UPPWhile toUPP(ArrayList<String> locals) {return new UPPWhile(cond.toUPP(locals), i.toUPP(locals));}

    public String toString() {return "while ("+cond.toString()+") do" + i.toString();}

}//PPWhile

class PPProcCall extends PPInst {

    Callee callee;
    ArrayList<PPExpr> args;

    PPProcCall (Callee callee, ArrayList<PPExpr> args) {
        this.callee = callee;
        this.args = args;
    }//PPProcCall

    UPPProcCall toUPP(ArrayList<String> locals){
        ArrayList<UPPExpr> arguments = new ArrayList<>();
        for (PPExpr arg : args){
            arguments.add(arg.toUPP(locals));
        }
        return new UPPProcCall(callee, arguments);
    }

    public String toString() {
        String toString ="";
        toString = callee.toString() + "( ";
        for (PPExpr arg : args) {
            toString += arg.toString() + ",";
        }
        toString += " )";
        return toString;
    }

}//PPProcCall
    
class PPSkip extends PPInst {
    UPPSkip toUPP(ArrayList<String> locals) {return new UPPSkip();}
    public String toString() {return "skip";}
}//PPSkip

class PPSeq extends PPInst {

    PPInst i1, i2;

    PPSeq (PPInst i1, PPInst i2) {
        this.i1 = i1;
        this.i2 = i2;
    }//PPSeq

    UPPSeq toUPP(ArrayList<String> locals) {return new UPPSeq(i1.toUPP(locals), i2.toUPP(locals));}

    public String toString() {return i1.toString() + "then" + i2.toString();}

}//PPSeq

/***************************************/
/* Definitions of functions/procedures */
/***************************************/

class Pair<L,R> {

    final L left;
    final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }//Pair

    static <L,R> Pair<L,R> of(L left, R right){
        return new Pair<L,R>(left, right);
    }//of

}//Pair

abstract class PPDef {

    String name;
    ArrayList<Pair<String,Type>> args, locals;
    PPInst code;

    abstract UPPDef toUPP(ArrayList<String> locals);

}//PPDef

class PPFun extends PPDef {

    Type ret;

    PPFun (String name, ArrayList<Pair<String,Type>> args,
         ArrayList<Pair<String,Type>> locals, PPInst code, Type ret) {
        this.name = name;
        this.args = args;
        this.locals = locals;
        this.code = code;
        this.ret = ret;
    }//PPFun

    UPPFun toUPP(ArrayList<String> locals){
        ArrayList<String> arguments = new ArrayList<>();
        ArrayList<String> localsUPP = new ArrayList<>();

        for (Pair<String, Type> arg : args) {
            arguments.add(arg.left);
        }
        
        for (Pair<String, Type> local : this.locals) {
            localsUPP.add(local.left);
        }

        return new UPPFun(this.name, arguments, localsUPP, code.toUPP(localsUPP));
    }

    public String toString() {
        String toString = name + "( ";
        for (Pair<String, Type> arg : args){
            toString += arg.left +" "+ arg.right.toString() +", ";
        }
        toString += ") : " + ret.toString();
        for (Pair<String, Type> local : locals) {
            toString += local.left +" "+ local.right.toString() +", ";
        }
        toString += code.toString();
        return toString;
    }
}//PPFun

class PPProc extends PPDef {

    PPProc (String name, ArrayList<Pair<String,Type>> args,
            ArrayList<Pair<String,Type>> locals, PPInst code) {
        this.name = name;
        this.args = args;
        this.locals = locals;
        this.code = code;
    }//PPProc

    UPPProc toUPP(ArrayList<String> locals) {
        ArrayList<String> arguments = new ArrayList<>();
        ArrayList<String> localsUPP = new ArrayList<>();

        for (Pair<String, Type> arg : args) {
            arguments.add(arg.left);
        }
        
        for (Pair<String, Type> local : this.locals) {
            localsUPP.add(local.left);
        }

        return new UPPProc(name, arguments, localsUPP, code.toUPP(localsUPP));
    }

    public String toString() {
        String toString = name + "( ";
        for (Pair<String, Type> arg : args){
            toString += arg.left +" "+ arg.right.toString() +", ";
        }
        toString += ") ";
        for (Pair<String, Type> local : locals) {
            toString += local.left +" "+ local.right.toString() +", ";
        }
        toString += code.toString();
        return toString;
    }

}//PPProc

/************/
/* Programs */
/************/

class PPProg {

    ArrayList<Pair<String,Type>> globals;
    ArrayList<PPDef> defs;
    PPInst code;

    PPProg (ArrayList<Pair<String,Type>> globals, ArrayList<PPDef> defs,
          PPInst code) {
        this.globals = globals;
        this.defs = defs;
        this.code = code;
    }//PPProg

    UPPProg toUPP() {
        ArrayList<String> globalsUPP = new ArrayList<>();
        ArrayList<UPPDef> defsUPP = new ArrayList<>();

        for (Pair<String, Type> global : this.globals) {
            globalsUPP.add(global.left);
        }
        
        for (PPDef def : this.defs) {
            defsUPP.add(def.toUPP(new ArrayList<String>()));
        }

        return new UPPProg(globalsUPP, defsUPP,code.toUPP(new ArrayList<String>()));
    }

    public String toString() {
        String toString = "( ";
        for (Pair<String, Type> vars : globals){
            toString +=(vars.left +" "+ vars.right.toString() +", ");
        }
        toString +=")";
        for (PPDef def : defs) {
            toString += def.toString() + ", ";
        }
        toString += ") (" + code.toString() +")";
        return toString;
    }
    
}//PPProg
