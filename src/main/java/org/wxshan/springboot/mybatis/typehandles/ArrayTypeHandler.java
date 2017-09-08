package org.wxshan.springboot.mybatis.typehandles;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.wxshan.springboot.utils.CommonUtils;

import java.sql.*;

/**
 * Created by Administrator on 2017/9/7 0007.
 */
public class ArrayTypeHandler extends BaseTypeHandler<Object[]> {

    private  static  final String TYPE_NAME_VARCHAR = "varchar";
    private static final String TYPE_NAME_INTEGER = "integer";
    private static final String TYPE_NAME_BOOLEAN = "boolean";
    private static final String TYPE_NAME_NUMERIC = "numeric";


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i,
                                    Object[] o, JdbcType jdbcType) throws SQLException {
        String typeName = null;
        if (o instanceof Integer[]){
            typeName = TYPE_NAME_INTEGER;
        } else if ( o instanceof String[]){
            typeName = TYPE_NAME_VARCHAR;
        } else if ( o instanceof  Boolean[]){
            typeName = TYPE_NAME_BOOLEAN;
        } else if ( o instanceof Double[]){
            typeName = TYPE_NAME_NUMERIC;
        }
        if (typeName == null){
            throw new TypeException("ArrayTypeHandler parameter typeName error, your type is " + o.getClass().getName());

        }

        Connection conn = preparedStatement.getConnection();
        Array array = conn.createArrayOf(typeName,o);
        preparedStatement.setArray(i, array);


    }

    @Override
    public Object[] getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return getArray(resultSet.getArray(columnName));
    }

    @Override
    public Object[] getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return getArray(resultSet.getArray(columnIndex));
    }

    @Override
    public Object[] getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return getArray(callableStatement.getArray(columnIndex));
    }

    private Object[] getArray(Array array){

        if (CommonUtils.isEmpty(array)){
            return null;
        }
        try {
            return (Object[]) array.getArray();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
