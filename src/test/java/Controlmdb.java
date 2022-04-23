import com.basepages.BaseTest;
import com.testpages.ImdbScenairo;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Controlmdb extends BaseTest {


    @Test
    @Parameters({"addUrlPlugin"})
    public void TesterYouTestCase(@Optional("") String addUrlPlugin) throws InterruptedException {

        new ImdbScenairo()
                .navigateToUrl(addUrlPlugin)
                .stepByStepCreate()
        ;
    }
}

