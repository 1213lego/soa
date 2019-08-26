package sample;

import com.sun.deploy.security.ValidationState;
import sample.model.db.ConnectionDb;
import sample.model.structural.Bike;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Prueba {
    public static void main(String [] args) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ConnectionDb connectionDb = ConnectionDb.getInstance();
        ResultSet rs = connectionDb.executeQueryStatement("select * from bike;");
        List asd = resultSetToListObjects(rs,Bike.class);
        Bike bike = new Bike("sdd34343", Bike.Type.GRAVEL,"GW",5656,646, LocalDateTime.now());
        System.out.println(bike.save());
    }
    static List resultSetToListObjects(ResultSet rs,Class typeClass) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException {
        List result = new ArrayList();
        ResultSetMetaData rsm = rs.getMetaData();
        Map<String,Method> methods = Arrays.asList(typeClass.getMethods()).stream().filter(method -> method.getName().
                contains("set")).
                collect(Collectors.toMap((method -> method.getName().toLowerCase()), Function.identity()));
        System.out.println(methods);
        while (rs.next()){
            Object actual = typeClass.newInstance();
            for(int i =1; i<= rsm.getColumnCount(); i ++){
                System.out.println(rsm.getColumnName(i));
                Method setMethod =
                methods.get("set" + rsm.getColumnName(i).toLowerCase());
                System.out.println(setMethod);
            }
        }
        return result;
    }
}
