import org.example.service.EscolaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class EscolaServiceTest {
    @Mock
    private ResultSet resultSet;
    @InjectMocks
    private EscolaService escolaService= mock(EscolaService.class);

    @Test
    void testConsultarAlunosPorCurso() throws SQLException {
        // Configurar o comportamento do resultSet para simular resultados
        when(resultSet.next()).thenReturn(true, true, false); // Duas linhas de resultados
        when(resultSet.getString("Nome")).thenReturn("Aluno1", "Aluno2");

        // Chamar o método consultarAlunosPorCurso
        escolaService.consultarAlunosPorCurso(1); // Suponha que o ID do curso seja 1

        // Capturar a saída impressa no console
        //String consoleOutput = outputStream.toString().trim(); // Remova espaços em branco no início e no fim

        // Verificar se a saída foi impressa corretamente
        String expectedOutput = "Alunos  matriculados nesse curso.\nNome do Aluno: Aluno1\nNome do Aluno: Aluno2";
        //assertEquals(expectedOutput, consoleOutput);
    }
}
