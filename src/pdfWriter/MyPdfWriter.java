package pdfWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Text;

import character.Skill;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import shadowRunApp.ShadowRunApp;

public class MyPdfWriter
{
	PdfPage page;
	PdfPage page2;
	PdfFont font;
	
	
	public void writeToPdf(character.Character myCharacter)
	{
		  FileChooser fileChooser = new FileChooser();
		  fileChooser.setTitle("Open Resource File");
		  fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
		  fileChooser.setInitialDirectory(new File("Shadow Run/myCharacters PDFs"));
		  fileChooser.setInitialFileName("Character.pdf");
		  File file = fileChooser.showSaveDialog(ShadowRunApp.primaryStage);
		  String filePath = file.getPath();
		  if (!filePath.contains(".pdf"))
			  filePath.concat(".pdf");
		    
		  try
		  {
			  createPdf(reusePDF(filePath), myCharacter);
		  } catch (IOException e)
		  {
			  e.printStackTrace();
		  }
	}
	
	public void createPdf(PdfDocument pdf, character.Character myCharacter) throws IOException {

		System.out.println(pdf);
		page = pdf.getPage(1);
		page2 = pdf.getPage(2);
		
		font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

		// Initialize document
		Document document = new Document(pdf);
		document.setFontColor(Color.BLACK);

		addCharacterName(myCharacter.getName());
		addMetatype(myCharacter.getMetatype());
//		addEthnicity(myCharacter.get());
		addAge(myCharacter.getAge());
		addGender(myCharacter.getGender());
		addHeight(myCharacter.getHeightFeet());
		addWeight(myCharacter.getWeight());
		addKarma(myCharacter.getKarma());
		addBody(myCharacter.getBody());
		addAgility(myCharacter.getAgility());
		addReaction(myCharacter.getReaction());
		addStrength(myCharacter.getStrength());
		addWillpower(myCharacter.getWillpower());
		addLogic(myCharacter.getLogic());
		addIntuition(myCharacter.getIntuition());
		addCharisma(myCharacter.getCharisma());
		addEdge(myCharacter.getEdge());
		addEssence(myCharacter.getEssence());
		addMagicResonance(myCharacter.getMagic());
		addInitiative();
		addMatrixInitiative();
		addAstralInitiative();
		addComposure();
		addJudgeIntentions();
		addMemory();
		addLiftCarry();
		addMovement();
		addLimits();
		addSkills(myCharacter.getSkills());
		addQualities();
		addContacts();
		addLifestyleandCurrency();
		addRangedWeapons();
		addMeleeWeapons();
		addArmor();
		addAugmentations();
		addGear();
		addSpells();
		addAdeptPowers();
		addCyberDeck();
		addVehicle();

		// Close document
		document.close();
	}

	public PdfDocument reusePDF(String dest) throws IOException {
		String src = "pdfSources/shadowrunCharacterSheet.pdf";
		PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

		PdfDocument origPdf = new PdfDocument(new PdfReader(src));
		PdfPage origPage = origPdf.getPage(1);
		PdfPage origPage2 = origPdf.getPage(2);

		pdf.addPage(origPage.copyTo(pdf));
		pdf.addPage(origPage2.copyTo(pdf));

		origPdf.close();

		return pdf;
	}

	private void addCharacterName(String name) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(115, 651).setColor(Color.BLACK, true)
				.showText(name).endText();
	}

	private void addMetatype(String metatype) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(75, 638)
				.showText(metatype).endText();
	}

	//TODO add string
	private void addEthnicity(String ethnicity) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(185, 638)
				.showText(ethnicity).endText();
	}

	//TODO add int
	private void addAge(int age) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(55, 625)
				.showText(Integer.toString(age)).endText();
	}

	//TODO add string
	private void addGender(String gender) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(109, 625)
				.showText(gender).endText();
	}

	//TODO add int
	private void addHeight(int height) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(170, 625)
				.showText(Integer.toString(height)).endText();
	}

	//TODO add int
	private void addWeight(int weight) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 625)
				.showText(Integer.toString(weight)).endText();
	}

	//TODO add int
	private void addKarma(int karma) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(63, 599)
				.showText(Integer.toString(karma)).endText();
	}

	//TODO int
	private void addBody(int body) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(90, 555)
				.showText(Integer.toString(body)).endText();
	}

	//TODO int
	private void addAgility(int agility) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(90, 542)
				.showText(Integer.toString(agility)).endText();
	}

	private void addReaction(int reaction) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(90, 529)
				.showText(Integer.toString(reaction)).endText();
	}

	private void addStrength(int strength) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(90, 516)
				.showText(Integer.toString(strength)).endText();
	}

	private void addWillpower(int willpower) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(90, 503)
				.showText(Integer.toString(willpower)).endText();
	}

	private void addLogic(int logic) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(90, 490)
				.showText(Integer.toString(logic)).endText();
	}

	private void addIntuition(int intuition) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(90, 477)
				.showText(Integer.toString(intuition)).endText();
	}

	private void addCharisma(int charisma) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(90, 464)
				.showText(Integer.toString(charisma)).endText();
	}

	private void addEdge(int edge) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(90, 451)
				.showText(Integer.toString(edge)).endText();
	}

	private void addEssence(int essence) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 555)
				.showText(Integer.toString(essence)).endText();
	}

	private void addMagicResonance(int magic) throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 542)
				.showText(Integer.toString(magic)).endText();
	}

	private void addInitiative() throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 529)
				.showText("197").endText();
	}

	private void addMatrixInitiative() throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 516)
				.showText("197").endText();
	}

	private void addAstralInitiative() throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 503)
				.showText("197").endText();
	}

	private void addComposure() throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 490)
				.showText("197").endText();
	}

	private void addJudgeIntentions() throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 477)
				.showText("197").endText();
	}

	private void addMemory() throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 464)
				.showText("197").endText();
	}

	private void addLiftCarry() throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 451)
				.showText("197").endText();
	}

	private void addMovement() throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(227, 438)
				.showText("197").endText();
	}

	private void addLimits() throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(90, 420)
				.showText("4").endText();
		PdfCanvas canvas1 = new PdfCanvas(page);
		canvas1.beginText().setFontAndSize(font, 10).moveText(165, 420)
				.showText("11").endText();
		PdfCanvas canvas2 = new PdfCanvas(page);
		canvas2.beginText().setFontAndSize(font, 10).moveText(247, 420)
				.showText("6").endText();
	}

	private void addSkills(ArrayList<Skill> skills) throws IOException {
		float x = 38, y = 366;
		int x2 = 117;
		boolean isPositive = true;
		// the max this loop should run is 32. if I have  more skills to write use extra sheet.
		for (int i = 0; i < 18; i++) {
			PdfCanvas canvas = new PdfCanvas(page);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("Cybercombat").endText();
			PdfCanvas canvas1 = new PdfCanvas(page);
			canvas1.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("14").endText();
			
//			if(isPositive)
//			{
//				Rectangle rect = new Rectangle(50,50,50,50);
//				PdfCanvas temp = new PdfCanvas(page);
//				temp.rectangle(rect).moveTo(x, y);
//			}
				
			y -= 11.9;
			isPositive = !isPositive;
			
		}

		x = 165;
		x2 = 244;
		y = 366;
		for (int i = 0; i < 18; i++) {
			PdfCanvas canvas = new PdfCanvas(page);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("Hacking").endText();
			PdfCanvas canvas1 = new PdfCanvas(page);
			canvas1.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("12").endText();
			y -= 11.9;
		}
	}

	private void addQualities() throws IOException {
		float x = 322, x2 = 466, y = 253;
		for (int i = 0; i < 10; i++) {
			PdfCanvas canvas = new PdfCanvas(page);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("Code Slinger").endText();
			PdfCanvas canvas1 = new PdfCanvas(page);
			canvas1.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("Hack on the Fly").endText();
			y -= 10.7;
		}
	}

	private void addContacts() throws IOException {
		float x = 322, x2 = 446, x3 = 476, y = 100;
		for (int i = 0; i < 5; i++) {
			PdfCanvas canvas = new PdfCanvas(page);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("Fixer").endText();
			PdfCanvas canvas1 = new PdfCanvas(page);
			canvas1.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("2").endText();
			PdfCanvas canvas2 = new PdfCanvas(page);
			canvas2.beginText().setFontAndSize(font, 10).moveText(x3, y)
					.showText("2").endText();
			y -= 13;
		}
	}

	private void addLifestyleandCurrency() throws IOException {
		PdfCanvas canvas = new PdfCanvas(page);
		canvas.beginText().setFontAndSize(font, 10).moveText(100, 110)
				.showText("Street").endText();
		PdfCanvas canvas1 = new PdfCanvas(page);
		canvas1.beginText().setFontAndSize(font, 10).moveText(60, 97)
				.showText("12345").endText();

		PdfCanvas canvas2 = new PdfCanvas(page);
		canvas2.beginText().setFontAndSize(font, 10).moveText(42, 71)
				.showText("Fake SIN").endText();
	}

	private void addRangedWeapons() throws IOException {
		float x = 42; // Weapon Name
		float x2 = 108; // Damage
		float x3 = 140; // Accuracy
		float x4 = 170; // AP
		float x5 = 193; // Mode
		float x6 = 233; // RC
		float x7 = 262; // Ammo
		float y = 648;
		for (int i = 0; i < 6; i++) {
			PdfCanvas canvas = new PdfCanvas(page2);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y).setColor(Color.BLACK, true)
					.showText("Streetline /////").endText();

			// Damage
			PdfCanvas canvas2 = new PdfCanvas(page2);
			canvas2.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("7").endText();

			// Accuracy
			PdfCanvas canvas3 = new PdfCanvas(page2);
			canvas3.beginText().setFontAndSize(font, 10).moveText(x3, y)
					.showText("5").endText();

			// AP
			PdfCanvas canvas4 = new PdfCanvas(page2);
			canvas4.beginText().setFontAndSize(font, 10).moveText(x4, y)
					.showText("1").endText();

			// Mode
			PdfCanvas canvas5 = new PdfCanvas(page2);
			canvas5.beginText().setFontAndSize(font, 10).moveText(x5, y)
					.showText("Burst").endText();

			// Recoil Compensation
			PdfCanvas canvas6 = new PdfCanvas(page2);
			canvas6.beginText().setFontAndSize(font, 10).moveText(x6, y)
					.showText("2").endText();

			// Ammo
			PdfCanvas canvas7 = new PdfCanvas(page2);
			canvas7.beginText().setFontAndSize(font, 10).moveText(x7, y)
					.showText("6").endText();
			y -= 12.9;
		}
	}

	private void addMeleeWeapons() throws IOException {
		float x = 322; // Weapon Name
		float x2 = 400; // reach
		float x3 = 450; // dam
		float x4 = 497; // ACC
		float x5 = 548; // AP
		float y = 648;
		for (int i = 0; i < 6; i++) {
			// Name
			PdfCanvas canvas = new PdfCanvas(page2);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("Forearm snapbl").endText();

			// REACH
			PdfCanvas canvas2 = new PdfCanvas(page2);
			canvas2.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("7").endText();

			// dam
			PdfCanvas canvas3 = new PdfCanvas(page2);
			canvas3.beginText().setFontAndSize(font, 10).moveText(x3, y)
					.showText("7").endText();

			// ACC
			PdfCanvas canvas4 = new PdfCanvas(page2);
			canvas4.beginText().setFontAndSize(font, 10).moveText(x4, y)
					.showText("6").endText();

			// AP
			PdfCanvas canvas5 = new PdfCanvas(page2);
			canvas5.beginText().setFontAndSize(font, 10).moveText(x5, y)
					.showText("3").endText();
			y -= 12.9;
		}
	}

	private void addArmor() throws IOException {
		float x = 42; // Armor Name
		float x2 = 160; // value
		float x3 = 233; // notes
		float y = 525;
		for (int i = 0; i < 6; i++) {
			PdfCanvas canvas = new PdfCanvas(page2);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("Armored Jacket").endText();

			// Damage
			PdfCanvas canvas2 = new PdfCanvas(page2);
			canvas2.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("7").endText();

			// Accuracy
			PdfCanvas canvas3 = new PdfCanvas(page2);
			canvas3.beginText().setFontAndSize(font, 10).moveText(x3, y)
					.showText("Notes").endText();

			y -= 12.9;
		}
	}

	private void addAugmentations() throws IOException {
		float x = 42; // Augmentation Name
		float x2 = 130; // rating
		float x3 = 183; // notes
		float x4 = 243; // Essence
		float y = 395;
		for (int i = 0; i < 7; i++) {
			PdfCanvas canvas = new PdfCanvas(page2);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("DataJack").endText();

			// Damage
			PdfCanvas canvas2 = new PdfCanvas(page2);
			canvas2.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("-").endText();

			// Accuracy
			PdfCanvas canvas3 = new PdfCanvas(page2);
			canvas3.beginText().setFontAndSize(font, 10).moveText(x3, y)
					.showText("Notes").endText();
			PdfCanvas canvas4 = new PdfCanvas(page2);
			canvas4.beginText().setFontAndSize(font, 10).moveText(x4, y)
					.showText("0.1").endText();
			y -= 14.5;
		}
	}

	private void addGear() throws IOException {
		float x = 42; // Gear name
		float x2 = 263; // rating
		float y = 252;
		for (int i = 0; i < 19; i++) {
			PdfCanvas canvas = new PdfCanvas(page2);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("Basic DocWagon").endText();

			// Damage
			PdfCanvas canvas2 = new PdfCanvas(page2);
			canvas2.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("5000").endText();
			y -= 11.6;
		}
	}

	private void addSpells() throws IOException {
		float x = 332; // Gear name
		float x2 = 392; // rating
		float x3 = 442;
		float x4 = 497;
		float x5 = 537;
		float y = 275;
		for (int i = 0; i < 8; i++) {
			PdfCanvas canvas = new PdfCanvas(page2);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("Fling").endText();
			// Damage
			PdfCanvas canvas2 = new PdfCanvas(page2);
			canvas2.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("M").endText();
			PdfCanvas canvas3 = new PdfCanvas(page2);
			canvas3.beginText().setFontAndSize(font, 10).moveText(x3, y)
					.showText("LOS").endText();
			// Damage
			PdfCanvas canvas4 = new PdfCanvas(page2);
			canvas4.beginText().setFontAndSize(font, 10).moveText(x4, y)
					.showText("S").endText();
			PdfCanvas canvas5 = new PdfCanvas(page2);
			canvas5.beginText().setFontAndSize(font, 10).moveText(x5, y)
					.showText("F - 3").endText();
			y -= 13.2;
		}
	}

	private void addCyberDeck() throws IOException {
		// Name
		Text text = new Text("6");
		text.setFontColor(Color.BLACK);
		PdfCanvas canvas = new PdfCanvas(page2);
		canvas.beginText().setFontAndSize(font, 10).moveText(352, 538)
				.showText("DECK").endText();

		// Attack
		PdfCanvas canvas2 = new PdfCanvas(page2);
		canvas2.beginText().setFontAndSize(font, 10).moveText(472, 538)
				.showText("2").endText();

		// Sleaze
		PdfCanvas canvas3 = new PdfCanvas(page2);
		canvas3.beginText().setFontAndSize(font, 10).moveText(552, 538)
				.showText("6").endText();

		// Device Rating
		PdfCanvas canvas4 = new PdfCanvas(page2);
		canvas4.beginText().setFontAndSize(font, 10).moveText(372, 525)
				.showText("6").endText();

		// Data Processing
		PdfCanvas canvas5 = new PdfCanvas(page2);
		canvas5.beginText().setFontAndSize(font, 10).moveText(472, 525)
				.showText("6").endText();

		// Firewall
		PdfCanvas canvas6 = new PdfCanvas(page2);
		canvas6.beginText().setFontAndSize(font, 10).moveText(552, 525)
				.showText("6").endText();
		float x = 332, y = 499;
		for (int i = 0; i < 11; i++) {
			if (i == 3 || i == 7) {
				if (i == 3)
					x = 412;
				else if (i == 7)
					x = 492;
				y = 512;
			}
			PdfCanvas canvas7 = new PdfCanvas(page2);
			canvas7.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("6").endText();
			y -= 13;
		}
	}

	private void addAdeptPowers() throws IOException {
		float x = 332; // Gear name
		float x2 = 462; // rating
		float x3 = 477;
		float y = 125;
		for (int i = 0; i < 8; i++) {
			PdfCanvas canvas = new PdfCanvas(page2);
			canvas.beginText().setFontAndSize(font, 10).moveText(x, y)
					.showText("Increase Skills").endText();
			// Damage
			PdfCanvas canvas2 = new PdfCanvas(page2);
			canvas2.beginText().setFontAndSize(font, 10).moveText(x2, y)
					.showText("2").endText();
			PdfCanvas canvas3 = new PdfCanvas(page2);
			canvas3.beginText().setFontAndSize(font, 10).moveText(x3, y)
					.showText("Throwing Weapons").endText();
			y -= 13.2;
		}
	}

	private void addVehicle() throws IOException {
		// Name
		PdfCanvas canvas = new PdfCanvas(page2);
		canvas.beginText().setFontAndSize(font, 10).moveText(355, 408)
				.showText("DECK").endText();

		// Accel
		PdfCanvas canvas2 = new PdfCanvas(page2);
		canvas2.beginText().setFontAndSize(font, 10).moveText(372, 395)
				.showText("2").endText();

		// Pilot
		PdfCanvas canvas3 = new PdfCanvas(page2);
		canvas3.beginText().setFontAndSize(font, 10).moveText(352, 382)
				.showText("6").endText();

		// Armor
		PdfCanvas canvas4 = new PdfCanvas(page2);
		canvas4.beginText().setFontAndSize(font, 10).moveText(352, 369)
				.showText("6").endText();

		// Handling
		PdfCanvas canvas5 = new PdfCanvas(page2);
		canvas5.beginText().setFontAndSize(font, 10).moveText(472, 408)
				.showText("6").endText();

		// Speed
		PdfCanvas canvas6 = new PdfCanvas(page2);
		canvas6.beginText().setFontAndSize(font, 10).moveText(472, 395)
				.showText("6").endText();
		
		// Body
		PdfCanvas canvas7 = new PdfCanvas(page2);
		canvas7.beginText().setFontAndSize(font, 10).moveText(472, 382)
				.showText("6").endText();

		// Sensor
		PdfCanvas canvas8 = new PdfCanvas(page2);
		canvas8.beginText().setFontAndSize(font, 10).moveText(472, 369)
				.showText("6").endText();

	}

}
