import org.example.model.Validacoes;
import org.example.service.CursoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @InjectMocks
    private CursoService cursoService;
    private final Validacoes validacoes= Mockito.mock(Validacoes.class);
    @Test
    void AdicionarCursoComSucesso()  {
        when(validacoes.validarProfessor(anyInt())).thenReturn(true);
        String name = "Ciências Contábeis";
        cursoService.inserirCurso(name,1);
    }
    @Test
    void AdicionarCursoComIdProfessorInvalido()  {
        when(validacoes.validarProfessor(anyInt())).thenReturn(false);
        String name = "Ciências Contábeis";
        cursoService.inserirCurso(name,1);
    }
    @Test
    void listarCursosComSucesso() throws SQLException {
        // Configure o comportamento do resultSet
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("NomeCurso")).thenReturn("Ciências Contábeis");
        when(resultSet.getString("NomeProfessor")).thenReturn("Roberto");
        // Configure o statement para retornar o resultSet mockado
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        // Chama o método listarAutores
        cursoService.consultarTodosCursos();

    }
    @Test
    public void AtualizarCursoComSucesso() throws SQLException {
        int idEmprestimo = 1;
        when(statement.executeUpdate(anyString())).thenReturn(1);
        cursoService.alterarCurso(idEmprestimo, "Engenharia de Software.");
    }
    @Test
    public void AtualizarCursoErrado() throws SQLException {
        int idEmprestimo = 1;
        when(statement.executeUpdate(anyString())).thenReturn(0);
        cursoService.alterarCurso(idEmprestimo, "Engenharia de Software.");
    }

}
