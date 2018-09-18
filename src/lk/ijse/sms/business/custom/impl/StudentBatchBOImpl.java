/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.sms.business.custom.StudentBatchBO;
import lk.ijse.sms.dao.custom.StudentBatchDAO;
import lk.ijse.sms.db.HibernateUtil;
import lk.ijse.sms.dto.StudentBatchDTO;
import lk.ijse.sms.entity.StudentBatch;
import lk.ijse.sms.entity.StudentBatch_PK;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sahan Rajakaruna
 */
@Component
public class StudentBatchBOImpl implements StudentBatchBO {

    @Autowired
    private StudentBatchDAO studentBatchDAO;
    private SessionFactory sessionFactory;
    public StudentBatchBOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean AddStudentBatch(StudentBatchDTO studentBatch) throws Exception {

        try (Session session = sessionFactory.openSession()) {
            studentBatchDAO.setSession(session);
            session.beginTransaction();
            studentBatchDAO.save(new StudentBatch(studentBatch.getId(),studentBatch.getBatch_bo()));
            session.getTransaction().commit();
            return true;
        }catch (HibernateException ex){
            return false;
        }

    }

    @Override
    public boolean UpdateStudentBatch(StudentBatchDTO studentBatch) throws Exception {

        try (Session session = sessionFactory.openSession()) {
            studentBatchDAO.setSession(session);
            session.beginTransaction();
            studentBatchDAO.update(new StudentBatch(studentBatch.getId(),studentBatch.getBatch_bo()));
            session.getTransaction().commit();
            return true;
        }catch (HibernateException ex){
            return false;
        }

    }

    @Override
    public boolean DeleteStudentBatch(String sid, String batch_id) throws Exception {

        try (Session session = sessionFactory.openSession()) {
            studentBatchDAO.setSession(session);
            session.beginTransaction();
            studentBatchDAO.delete(new StudentBatch_PK(sid,batch_id));
            session.getTransaction().commit();
            return true;
        }catch (HibernateException ex){
            return false;
        }
    }

    @Override
    public StudentBatchDTO findById(String sid, String batch_id) throws Exception {

        try (Session session = sessionFactory.openSession()) {
            studentBatchDAO.setSession(session);
            session.beginTransaction();
            StudentBatch student = studentBatchDAO.findByID(new StudentBatch_PK(sid, batch_id));
            session.getTransaction().commit();
            return new StudentBatchDTO(student.getStudent().getSid(),student.getBatch().getBatch_no());
        }catch (HibernateException ex){
            return null;
        }
    }

    @Override
    public ArrayList<StudentBatchDTO> allStudentBatchs() throws Exception {

        try (Session session = sessionFactory.openSession()) {
            studentBatchDAO.setSession(session);
            session.beginTransaction();
            List<StudentBatch> allStudentBatchEntity = studentBatchDAO.getAll();
            ArrayList<StudentBatchDTO> allStudentBatchDTO = new ArrayList<>();
            session.getTransaction().commit();
            for (StudentBatch studentBatch : allStudentBatchEntity) {

                allStudentBatchDTO.add(new StudentBatchDTO(studentBatch.getStudentBatch_PK().getSid(), studentBatch.getStudentBatch_PK().getBatch_no()));

            }
            return allStudentBatchDTO;
            }catch (HibernateException ex){
            return null;
        }
    }

}
