package lk.ijse.sms.dao;

import lk.ijse.sms.entity.Exam;
import lk.ijse.sms.entity.StudentExam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class CrudDAOImpl<T ,ID> implements CrudDAO<T,ID>
{

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>)(((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) throws Exception {

        getSession().persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        getSession().update(entity);
    }

    @Override
    public void delete(ID id) throws Exception {
        T t = getSession().find(entity, id);
        getSession().remove(t);
    }

    @Override
    public T findByID(ID id) throws Exception {
        return  getSession().find(entity, id);
    }

    @Override
    public List<T> getAll() throws Exception {
        System.out.println(entity.getName());
       return getSession().createQuery("FROM "+entity.getName()).list();
    }

//    @Override
//    public void setSession(Session session) {
//        this.session=session;
//    }


}
