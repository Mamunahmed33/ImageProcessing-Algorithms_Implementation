package FileChooser_Saver;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jtattoo.plaf.noire.NoireLookAndFeel;

public class FileChooser extends JFrame{
	private static FileChooser instance= null;
	
	private FileChooser() {
		
	}
	
	public static FileChooser getFileChooserInstance() {
		if(instance == null)
		{
			instance = new FileChooser();
		}
		
		return instance;
	}
	
	public String getFile(String initialPath) {
		
		try {
			//	UIManager.setLookAndFeel(new BernsteinLookAndFeel());
			//	UIManager.setLookAndFeel(new AcrylLookAndFeel());
			//	UIManager.setLookAndFeel(new AluminiumLookAndFeel());
			//	UIManager.setLookAndFeel(new FastLookAndFeel());
			//	UIManager.setLookAndFeel(new GraphiteLookAndFeel());
			//	UIManager.setLookAndFeel(new LunaLookAndFeel());
			//	UIManager.setLookAndFeel(new McWinLookAndFeel());
			//	UIManager.setLookAndFeel(new MintLookAndFeel());
				UIManager.setLookAndFeel(new NoireLookAndFeel());
			//	UIManager.setLookAndFeel(new SmartLookAndFeel());
				//UIManager.setLookAndFeel(new TextureLookAndFeel());
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		JFileChooser fChooser = new JFileChooser();
		FileNameExtensionFilter fileFilter = new  FileNameExtensionFilter("Images", "JPG", "JPEG", "png");
		fChooser.setFileFilter(fileFilter);
				
		fChooser.setCurrentDirectory(new File(initialPath));
		int filechoosen = fChooser.showOpenDialog(new JFrame());
		
		if(filechoosen == fChooser.APPROVE_OPTION){
			
			return fChooser.getSelectedFile().toString();
		}
		
		return "";
	}
	
}
