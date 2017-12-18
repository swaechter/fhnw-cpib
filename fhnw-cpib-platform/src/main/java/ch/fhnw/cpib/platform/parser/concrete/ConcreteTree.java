package ch.fhnw.cpib.platform.parser.concrete;

import ch.fhnw.cpib.platform.scanner.tokens.identifier.IdentifierToken;

public class ConcreteTree {

    public static class Program extends Item {

        private final IdentifierToken identifier;

        private final ProgParamList progparamlist;

        private final OptCpsDecl optcpsdecl;

        private final CpsCmd cpscmd;

        public Program(IdentifierToken identifier, ProgParamList progparamlist, OptCpsDecl optcpsdecl, CpsCmd cpscmd) {
            this.identifier = identifier;
            this.progparamlist = progparamlist;
            this.optcpsdecl = optcpsdecl;
            this.cpscmd = cpscmd;
        }

        @Override
        public String toString() {
            return "" + getIdent() + identifier + " " + progparamlist + " " + optcpsdecl + " " + cpscmd;
        }
    }

    public static class Decl extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class StoDecl extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class FunDecl extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class ProcDecl extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptGlobImps extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class GlobImps extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepGlobImps extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptChangemode extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptMechmode extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class GlobImp extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptCpsDecl extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class CpsDecl extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepCpsDecl extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptCpsStoDecl extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class CpsStoDecl extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepCpsStoDecl extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class ProgParamList extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptProgParamList extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepProgParamList extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class ProgParam extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class ParamList extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptParamList extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepParamList extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class Param extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class TypedIdent extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class Cmd extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class CpsCmd extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepCpsCmd extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptGlobInits extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepIdents extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class Expr extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepTerm1 extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class Term1 extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepTerm2 extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class Term2 extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepTerm3 extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class Term3 extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepFactor extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class Factor extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptInitOrExprList extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class MonadicOpr extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class ExprList extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptExprList extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepExprList extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepCase extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptDefault extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class OptElse extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

    public static class RepElseif extends Item {

        @Override
        public String toString() {
            return "TODO";
        }
    }

}
