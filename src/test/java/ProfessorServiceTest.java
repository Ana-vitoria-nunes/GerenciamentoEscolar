import org.example.service.ProfessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @InjectMocks
    private ProfessorService professorService;
    @Test
    void AdicionarProfessorComSucesso() throws SQLException {
        when(statement.executeUpdate(anyString())).thenReturn(0);
        professorService.inserirProfessor("Roberto","Análise Financeira");
    }
    @Test
    void listarProfessoresComSucesso() throws SQLException {
        // Configure o comportamento do resultSet
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("Nome")).thenReturn("Roberto");
        when(resultSet.getString("Disciplina")).thenReturn("Análise Financeira");
        // Configure o statement para retornar o resultSet mockado
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        // Chama o método listarAutores
        professorService.consultarTodosProfessores();

    }
    @Test
    public void AtualizarCursoComSucesso() throws SQLException {
        int idEmprestimo = 1;
        when(statement.executeUpdate(anyString())).thenReturn(1);
        professorService.alterarProfessor(idEmprestimo, "Engenharia de Software.");
    }
    @Test
    public void AtualizarCursoErrado() throws SQLException {
        int idEmprestimo = 1;
        when(statement.executeUpdate(anyString())).thenReturn(0);
        professorService.alterarProfessor(idEmprestimo, "Engenharia de Software.");
    }

}
