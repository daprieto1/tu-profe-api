package com.tuprofe.api.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author diego
 */
@DynamoDBTable(tableName = "teacher-history")
public class TeacherHistory implements Serializable {

    private String teacherId;
    private Long date;
    private Integer state;

    public TeacherHistory() {
        this.date = (new Date()).getTime();
    }

    public TeacherHistory(String teacherId, Integer state) {
        this();
        this.teacherId = teacherId;
        this.state = state;
    }

    @DynamoDBHashKey(attributeName = "teacherId")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @DynamoDBRangeKey(attributeName = "date")
    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @DynamoDBAttribute(attributeName = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
