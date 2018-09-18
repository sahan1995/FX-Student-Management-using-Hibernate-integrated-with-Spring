/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.sms.business.custom.StudentExamBO;
import lk.ijse.sms.dao.custom.ExamDAO;
import lk.ijse.sms.dao.custom.StudentDAO;
import lk.ijse.sms.dao.custom.StudentExamDAO;
import lk.ijse.sms.db.HibernateUtil;
import lk.ijse.sms.dto.StudentExamDTO;
import lk.ijse.sms.entity.Exam;
import lk.ijse.sms.entity.Student;
import lk.ijse.sms.entity.StudentExam;
import lk.ijse.sms.entity.StudentExam_PK;
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
public class StudentExamBOImpl implements StudentExamBO {

    @Autowired
    private StudentExamDAO studentExamDAO;
    private SessionFactory sessionFactory;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private ExamDAO examDAO;
    public StudentExamBOImpl() {

        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean AddStudentExam(StudentExamDTO studentExam) throws Exception {

//        System.out.println(studentExam.getExam_id()+" "+studentExam.getId()+" "+studentExam.getMarks());
        try (Session session = sessionFactory.openSession()) {
            studentExamDAO.setSession(session);
            examDAO.setSession(session);
            studentDAO.setSession(session);
            session.beginTransaction();
            Exam exam = examDAO.findByID(studentExam.getExam_id());
            Student student = studentDAO.findByID(studentExam.getId());
            StudentExam studentExam1 = new StudentExam(student, exam, studentExam.getMarks());
            student.addStudentExamList(studentExam1);
            exam.addStudentExam(studentExam1);
//            studentExamDAO.save(new StudentExam(student,exam,studentExam.getMarks()));
            session.getTransaction().commit();
            return true;
        }catch (HibernateException ex){
            return false;
        }
//




    }

    @Override
    public boolean UpdateStudentExam(StudentExamDTO studentExam) throws Exception {

//        return studentExamDAO.update(new StudentExam(studentExam.getId(), studentExam.getExam_id(), studentExam.getMarks()));
        return false;
    }

    @Override
    public boolean DeleteStudentExam(String sid, int exam_id) throws Exception {
//        return studentExamDAO.delete(new StudentExam_PK(sid, exam_id));
        return false;
    }

    @Override
    public StudentExamDTO FindbyID(String sid, int exam_id) throws Exception {
        StudentExam studentExamEntity = studentExamDAO.findByID(new StudentExam_PK(sid, exam_id));
        return new StudentExamDTO(studentExamEntity.getStudentExam_PK().getSidm(), studentExamEntity.getStudentExam_PK().getExamid(), studentExamEntity.getMarks());

    }

    @Override
    public ArrayList<StudentExamDTO> allStudentExamD() throws Exception {
//        ArrayList<StudentExamDTO> allStudentExamDTO = new ArrayList<>();
//        ArrayList<StudentExam> allStudentExamsEntity = studentExamDAO.getAll();
//        for (StudentExam studentExam : allStudentExamsEntity) {
//            allStudentExamDTO.add(new StudentExamDTO(studentExam.getStudentExam_PK().getSidm(), studentExam.getStudentExam_PK().getExamid(), studentExam.getMarks()));
//
//        }
//        return allStudentExamDTO;
        return null;
    }

}
