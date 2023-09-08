import org.example.model.Validacoes;
import org.example.service.MatriculaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class MatriculaServiceTest {
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @InjectMocks
    private MatriculaService matriculaService;
    private final Validacoes validacoes= Mockito.mock(Validacoes.class);
    @Test
    void AdicionarMatriculaComSucesso()  {
        when(validacoes.validarAluno(anyInt())).thenReturn(true);
        when(validacoes.validarCurso(anyInt())).thenReturn(true);
        when(validacoes.ValidarAlunoMatriculaCurso(anyInt(), anyInt())).thenReturn(false);
        matriculaService.inserirMatricula(1,1, LocalDate.now());
    }
    @Test
    void AdicionarMatriculaComIdAlunoInvalido()  {
        when(validacoes.validarAluno(anyInt())).thenReturn(false);
        when(validacoes.validarCurso(anyInt())).thenReturn(true);
        when(validacoes.ValidarAlunoMatriculaCurso(anyInt(), anyInt())).thenReturn(false);
        matriculaService.inserirMatricula(1,1, LocalDate.now());
    }
    @Test
    void AdicionarMatriculaComIdCursoInvalido()  {
        when(validacoes.validarAluno(anyInt())).thenReturn(true);
        when(validacoes.validarCurso(anyInt())).thenReturn(false);
        when(validacoes.ValidarAlunoMatriculaCurso(anyInt(), anyInt())).thenReturn(true);
        matriculaService.inserirMatricula(1,1, LocalDate.now());
    }
    @Test
    void AdicionarMatriculaParaAlunoJaMatriculado()  {
        when(validacoes.validarAluno(anyInt())).thenReturn(true);
        when(validacoes.validarCurso(anyInt())).thenReturn(true);
        when(validacoes.ValidarAlunoMatriculaCurso(anyInt(), anyInt())).thenReturn(true);
        matriculaService.inserirMatricula(1,1, LocalDate.now());
    }
    @Test
    void deletarMatriculaComSucesso() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(1);
        matriculaService.deletarMatricula(1);
    }
    @Test
    void deletarMatriculaComIdErrado() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        matriculaService.deletarMatricula(1);
    }
    @Test
    void listarCursosComSucesso() throws SQLException {
        // Configure o comportamento do resultSet
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("NomeAluno")).thenReturn("Raissa");
        when(resultSet.getString("NomeCurso")).thenReturn("Ciências");
        when(resultSet.getString("DataMatricula")).thenReturn("2023-09-08");
        // Configure o statement para retornar o resultSet mockado
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        // Chama o método listarAutores
        matriculaService.consultarTodasMatriculas();
    }

}
