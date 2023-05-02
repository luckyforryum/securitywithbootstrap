package ru.kata.spring.boot_security.demo.Conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;



@Component
public class StringToRoleConverter implements Converter<String, Role> {

    private final RoleService roleService;
    @Autowired
    public StringToRoleConverter(RoleService roleService) {
        this.roleService = roleService;
    }


    @Override
    public Role convert(String source) {
        Long id = Long.valueOf(source);
        return roleService.getRoleById(id);
    }
}
