package com.tuprofe.api.persistance.implementation;

import com.tuprofe.api.entities.Induction;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.IInductionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
@Qualifier("DynamoInductionRepository")
public class DynamoInductionRepository extends DAODynamo<Induction, String> implements IInductionRepository {

    public DynamoInductionRepository() {
        super(Induction.class);
    }
}
