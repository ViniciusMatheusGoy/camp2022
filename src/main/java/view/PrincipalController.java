package view;

import Model.Jogo;
import Model.Time;
import Utilit.Dados;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {

    private List<Time> lstClassificacao = new ArrayList<Time>();
    private Dados dados;
    private Time timeSel;

    @FXML
    public Button btnAbrirArquivo;

    @FXML
    public TableView<Time> tblvwClassificacao;

    @FXML
    public TableView<Jogo> tblvwJogosTimeSelecionado;

    @FXML
    private void btnAbrirArquivoClick() {
        final Stage stage = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir arquivo .TXT");
        File teste = new File("C:\\Teste");
        fileChooser.setInitialDirectory(new File("src\\main\\resources\\file_sources"));
        fileChooser.setInitialFileName("campeonato");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivos TXT", "*.txt"),
                new FileChooser.ExtensionFilter("Todos os arquivos", "*.*"));
        File selectedFile;
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            dados = new Dados(selectedFile.toString());
            lstClassificacao = dados.lerJogo();
            tblvwClassificacao.setItems(FXCollections.observableList(lstClassificacao));
        }
    }

    @FXML
    public StackPane spJogos;

    @FXML
    public Label lblTimeSel;

    @FXML
    private void tblvwClassificacaoClick(Event event) {
        MouseEvent me = null;
        if (event.getEventType() == MOUSE_CLICKED) {
            me = (MouseEvent) event;
            if (me.getClickCount() == 2) {
                spJogos.setVisible(true);
                timeSel = tblvwClassificacao.getSelectionModel().getSelectedItem();
                tblvwJogosTimeSelecionado.requestFocus();
                lblTimeSel.setText(timeSel.getNome());
                tblvwJogosTimeSelecionado.setItems(FXCollections.observableArrayList(timeSel.getJogos()));
            }
        }
    }

    @FXML
    private Button btnFecharJogos;

    @FXML
    private void btnFecharJogosClick(Event event) {
        spJogos.setVisible(false);
    }

    @FXML
    private Button btnSalvarJogos;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        spJogos.setVisible(false);

    }
}
