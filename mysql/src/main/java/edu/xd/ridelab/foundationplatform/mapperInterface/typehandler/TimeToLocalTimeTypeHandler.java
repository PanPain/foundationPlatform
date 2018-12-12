package edu.xd.ridelab.foundationplatform.mapperInterface.typehandler;

import org.apache.ibatis.type.*;

import java.sql.*;
import java.sql.Time;
import java.time.LocalTime;

@MappedTypes({LocalTime.class})
@MappedJdbcTypes(JdbcType.TIME)
public class TimeToLocalTimeTypeHandler extends BaseTypeHandler<LocalTime> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalTime localTime, JdbcType jdbcType) throws SQLException {
        preparedStatement.setTime(i,new Time(localTime.getHour(),localTime.getMinute(),localTime.getSecond()));
    }

    @Override
    public LocalTime getNullableResult(ResultSet resultSet, String column) throws SQLException {
        Time time  = resultSet.getTime(column);
        if(time == null)
            return null;
        LocalTime localTime = LocalTime.of(time.getHours(),time.getMinutes(),time.getSeconds());
        return localTime;
    }

    @Override
    public LocalTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Time time  = resultSet.getTime(i);
        if(time == null)
            return null;
        LocalTime localTime = LocalTime.of(time.getHours(),time.getMinutes(),time.getSeconds());
        return localTime;
    }

    @Override
    public LocalTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Time time  = callableStatement.getTime(i);
        if(time == null)
            return null;
        LocalTime localTime = LocalTime.of(time.getHours(),time.getMinutes(),time.getSeconds());
        return localTime;
    }
}
