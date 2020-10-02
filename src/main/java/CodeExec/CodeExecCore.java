package CodeExec;

import org.apache.commons.jexl3.*;
import org.apache.commons.jexl3.internal.Engine;

import java.util.Map;

public class CodeExecCore {
    public static Object ExecStringCode(String ProcessExp, Map<String, Object> Code) {
        JexlEngine FunctionEngine = new JexlBuilder().create();
        JexlExpression Exp = FunctionEngine.createExpression(ProcessExp);
        JexlContext ProcessCode = new MapContext();

        for(String Key : Code.keySet()) { ProcessCode.set(Key, Code.get(Key)); }

        return Exp.evaluate(ProcessCode);
    }
}
