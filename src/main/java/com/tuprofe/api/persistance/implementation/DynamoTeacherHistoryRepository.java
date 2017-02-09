package com.tuprofe.api.persistance.implementation;

import com.tuprofe.api.entities.TeacherHistory;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.ITeacherHistoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
@Qualifier("DynamoTeacherHistoryRepository")
public class DynamoTeacherHistoryRepository extends DAODynamo<TeacherHistory, String> implements ITeacherHistoryRepository {

    public DynamoTeacherHistoryRepository() {
        super(TeacherHistory.class);
    }

}
