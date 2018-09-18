/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.sms.business.custom.impl;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.sms.business.custom.ModuleBO;
import lk.ijse.sms.dao.custom.ModuleDAO;
import lk.ijse.sms.db.HibernateUtil;
import lk.ijse.sms.dto.ModuleDTO;
import lk.ijse.sms.entity.Module;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @author Sahan Rajakaruna
 */
@Component
@Transactional
public class ModuleBOImpl implements ModuleBO {

    @Autowired
    private ModuleDAO moduleDAO;

    public ModuleBOImpl() {


    }

    @Override
    public boolean AddModule(ModuleDTO module) throws Exception {

        try {

            moduleDAO.save(new Module(module.getModule_id(), module.getModule_name()));

            return true;


        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public boolean UpdateModule(ModuleDTO module) throws Exception {


        try {

            moduleDAO.update(new Module(module.getModule_id(), module.getModule_name()));
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;

        }


    }

    @Override
    public boolean DeleteModule(String id) throws Exception {

        try {
            moduleDAO.delete(id);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }


    }

    @Override
    public ModuleDTO findById(String id) throws Exception {
        Module moduleEntity = moduleDAO.findByID(id);
        if (moduleEntity != null) {
            return new ModuleDTO(moduleEntity.getModule_id(), moduleEntity.getModule_name());
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<ModuleDTO> allModules() throws Exception {

        ArrayList<ModuleDTO> allModuleDTO = new ArrayList<>();
        try {
            List<Module> allModules = moduleDAO.getAll();
            System.out.println("Hello");
            System.out.println(allModules);
            for (Module module : allModules) {
                allModuleDTO.add(new ModuleDTO(module.getModule_id(), module.getModule_name()));
            }

            return allModuleDTO;

        } catch (HibernateException ex) {
            System.out.println("HERE");
            System.out.println(ex);
            return null;
        }


    }

    @Override
    public String generateCustomId() throws Exception {
//        String lastID = moduleDAO.getLastID();
//
//        String[] part = lastID.split("MOD");
//        int no = Integer.parseInt(part[1]);
//        no++;
//        String customID = "MOD" + no;
//        return customID;
        return null;
    }

    @Override
    public ArrayList<String> getID(String... args) throws Exception {
        return moduleDAO.getModuleId(args);
    }

    @Override
    public ArrayList<String> getCourseModules(String course_id) throws Exception {
        return moduleDAO.getModules(course_id);
    }

    @Override
    public String getModuleID(String course_title) throws Exception {
//        return moduleDAO.getModuleId(course_title);
        return null;
    }

    @Override
    public int moduleCount() throws Exception {
//        return moduleDAO.moduleCount();
        return 0;
    }

}
