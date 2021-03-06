package br.ce.wcaquino.taskbackend.controller;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.Before;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;
    @InjectMocks
    private TaskController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ValidationException.class)
    public void naoDeveSalvarTarefaSemDescricao() throws ValidationException {
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());
        controller.save(todo);
    }

    @Test(expected = ValidationException.class)
    public void naoDeveSalvarTarefaSemData() throws ValidationException {
        Task todo = new Task();
        todo.setTask("Descrição");
        controller.save(todo);
    }

    @Test(expected = ValidationException.class)
    public void naoDeveSalvarTarefaComDataPassada() throws ValidationException {
        Task todo = new Task();
        todo.setTask("Descrição");
        todo.setDueDate(LocalDate.of(2010, 1, 1));
        controller.save(todo);
    }

    public void deveSalvarTarefaComSucesso()throws ValidationException {
        Task todo = new Task();
        todo.setTask("Descrição");
        todo.setDueDate(LocalDate.now());
        controller.save(todo);
    }
}