/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom;

import lk.ijse.sms.business.SuperBO;
import lk.ijse.sms.dto.StudentBatchDTO;
import lk.ijse.sms.dto.StudentDTO;

/**
 *
 * @author Sahan Rajakaruna
 */
public interface RegistrationBO extends SuperBO {

    public boolean RegisterNewStudent(StudentDTO student, StudentBatchDTO studentBatch) throws Exception;
    
    public boolean RegisterToBatch(StudentBatchDTO studentBatch) throws Exception;
}
