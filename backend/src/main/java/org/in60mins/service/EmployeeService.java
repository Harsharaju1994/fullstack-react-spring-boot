package org.in60mins.service;


import org.in60mins.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService
{

    EmployeeDto addEmployee(EmployeeDto employeeDto);
    EmployeeDto getById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto editById(Long employeeid,EmployeeDto employeeDto);
    void deleteById(Long employeeId);
}
