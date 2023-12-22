package org.in60mins.controller;

import lombok.AllArgsConstructor;
import org.in60mins.dto.EmployeeDto;
import org.in60mins.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmployeeController
{

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createTecher(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable("id") Long teacherId){
        EmployeeDto teacher = employeeService.getById(teacherId);
        return ResponseEntity.ok(teacher);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable("id") Long employeeid,
                                             @RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeService.editById(employeeid,employeeDto);
        return ResponseEntity.ok(employeeDto1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteById(employeeId);
        return ResponseEntity.ok(employeeId+" : deleted Successfully...!");
    }
}
