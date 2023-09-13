package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;

import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Poc;
import com.example.demo.entity.PocDes;
import com.example.demo.entity.ServiceType;
import com.example.demo.entity.Test;
import com.example.demo.entity.Vulnerabilityfound;
import com.example.demo.service.ServiceImplementations;

@RestController
@RequestMapping("test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

	private ServiceImplementations serimp;

	@Autowired
	public TestController(ServiceImplementations serimp) {
		this.serimp = serimp;
	}
	
	
	@GetMapping("/list")
	public List<Test> displayAllTest(){
		List<Test> testList = serimp.DisplayAllTests();
		return testList;
	}
	
	@GetMapping("/list/{id}")
	public Test displayStypeById(@PathVariable("id") int id){
		Test test = serimp.getTestById(id);
		return test;
	}
	
	@PostMapping("/list")
	public void insertTest(@RequestBody Test test) {
		serimp.insertTest(test);
		
	}

	@DeleteMapping("/list/{id}")
	public void DeleteTest(@PathVariable("id") int id) {
		serimp.deleteTest(id);
	}
	
	@PutMapping("/list")
	public void updateTest(@RequestBody Test test) {
		serimp.updateTest(test);
	}
	
	@GetMapping("/files/{id}")
	public ResponseEntity<Resource> downloadop(@PathVariable("id") int id) throws IOException, InvalidFormatException{
		Test test = serimp.getTestById(id);
		//return test;
		List<Vulnerabilityfound> vfound = test.getVulnerabilities();
		List<Vulnerabilityfound> sortedvul = serimp.sortVul(vfound);
		genreport(sortedvul);
		
		
		
		
		Resource fileResource =   new FileSystemResource("./src/report2.docx");; // Load your file resource here
		File file = fileResource.getFile();
	       // Set appropriate headers for the response
	       org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	       headers.add(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\report.docx\"" );
	       
	       // Return the file as a ResponseEntity
	       return ResponseEntity.ok()
	               .headers(headers)
	               .body(fileResource);
	}
	

	public void conbytoimg(byte[] byt) throws IOException {
	      ByteArrayInputStream bis = new ByteArrayInputStream(byt);
	      BufferedImage bImage2 = ImageIO.read(bis);
	      ImageIO.write(bImage2, "png", new File("./src/pic.png") );
	}
	
	

	
	public void genreport(List<Vulnerabilityfound> vfoundlist) throws InvalidFormatException, IOException {
		XWPFDocument document = new XWPFDocument(new FileInputStream(new File("./src/header2.docx"))); // Create a new document
		

		
		for(int m=0;m<vfoundlist.size();m++) {
			Vulnerabilityfound vfound = vfoundlist.get(m);
		
			List<Poc> plist = vfound.getPoc();
		

		// Load an existing document using XWPFDocument(InputStream)
		int numRows = 9;
		int numCols = 2;
		XWPFTable table = document.createTable(numRows, numCols);
		
		XWPFTableRow tableRow1 = table.getRow(0);
	      tableRow1.setHeight(10);
	      tableRow1.getCell(0).setText("Observation No "+(m+1));
	      tableRow1.getCell(0).setColor("FF0000");
	      tableRow1.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
	      
	      XWPFTableCell cellToMerge = tableRow1.getCell(0); // For example, cell at row 1, column 1
	      XWPFTableCell targetCell = tableRow1.getCell(1); // For example, cell at row 2, column 2

	      if (cellToMerge.getCTTc().getTcPr() == null) cellToMerge.getCTTc().addNewTcPr();
	      if (cellToMerge.getCTTc().getTcPr().isSetGridSpan()) cellToMerge.getCTTc().getTcPr().unsetGridSpan();
	      cellToMerge.getCTTc().getTcPr().addNewGridSpan().setVal(BigInteger.valueOf(2)); // Merge two cells horizontally

	      // Remove the paragraph in the merged cell
	      for (int i = cellToMerge.getParagraphs().size() - 1; i > 0; i--) {
	          cellToMerge.removeParagraph(i);
	      }
	      
	      //tableRow1.removeCell(1);
	      XWPFTableCell removed = tableRow1.getCell(1);
	      removed.getCTTc().newCursor().removeXml();
	      tableRow1.removeCell(1);
	      
	     
	      int[] columnWidths = {2000, 6700};
	      
	      for (int i = 1; i < table.getNumberOfRows()-2; i++) {
	            XWPFTableRow row = table.getRow(i);
	            for (int j = 0; j < row.getTableCells().size(); j++) {
	                XWPFTableCell cell = row.getCell(j);
	                CTTblWidth width = cell.getCTTc().addNewTcPr().addNewTcW();
	                width.setType(STTblWidth.DXA); // Set width type to twentieths of a point
	                width.setW(BigInteger.valueOf(columnWidths[j]));
	            }
	        }
	      

	      
	      String[] colors = {"800808","FF0000","F4C430","DFFF00","32CD32"};
	      String[] severity = {"Critical","High","Medium","Low","Informational"};
	      
	      XWPFTableRow tableRowtwo = table.getRow(1);
	      tableRowtwo.getCell(0).setText("Vulnerability");
	      tableRowtwo.getCell(1).setText(vfound.getVfname());
	      tableRowtwo.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

	      XWPFTableRow tableRow3 = table.getRow(2);
	      
	      tableRow3.getCell(0).setText("Severity");
	      tableRow3.getCell(1).setText(vfound.getSeverity());
	      tableRow3.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
	    
	      for(int sev=0;sev<severity.length;sev++) {
	    	  if(vfound.getSeverity().equalsIgnoreCase(severity[sev])) {
	    		  tableRow3.getCell(1).setColor(colors[sev]);
	    	  }
	      }
	      
	      
//	      
	      XWPFTableRow tableRow4 = table.getRow(3);
	    
	      tableRow4.getCell(0).setText("Description");
	      tableRow4.getCell(1).setText(vfound.getDescription());
	      tableRow4.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
	      
	      
	      XWPFTableRow tableRow5 = table.getRow(4);
	      
	      tableRow5.getCell(0).setText("Remediation");
	      tableRow5.getCell(1).setText(vfound.getRemediation());
	      tableRow5.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
//
	      XWPFTableRow tableRow6 = table.getRow(5);
//	     
	      tableRow6.getCell(0).setText("Affected Files");
	      tableRow6.getCell(1).setText(vfound.getAffectedurls());
	      tableRow6.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
//	      
	      XWPFTableRow tableRow7 = table.getRow(6);
//    
	      tableRow7.getCell(0).setText("References");
	      tableRow7.getCell(1).setText(vfound.getVreferences());
	      tableRow7.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
	      
	      XWPFTableRow tableRow8 = table.getRow(7);
//	     
	      tableRow8.getCell(0).setText("Proof of Concept");
	      tableRow8.getCell(1).setText("See Below");
	      tableRow8.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
	      
	      
//	      for(int j=8;j<8+vfound.getPoc().size();j++) {
//	    	  
//	    	  
//	    	  	Poc pc = plist.get(j-8);
//	  			byte[] byt = pc.getPic_byte();
//	  			//System.out.println(byt);
//	  			conbytoimg(byt);
//	  		
//	  		
//	      
//	      	XWPFTableCell cell = null;
//			XWPFTableRow tableRow9 = table.getRow(j);
//
//			// First Row
//
//			XWPFTableCell cellToMerge1 = tableRow9.getCell(0); // For example, cell at row 1, column 1
//			XWPFTableCell targetCell1 = tableRow9.getCell(1); // For example, cell at row 2, column 2
//
//			if (cellToMerge1.getCTTc().getTcPr() == null)
//				cellToMerge1.getCTTc().addNewTcPr();
//			if (cellToMerge1.getCTTc().getTcPr().isSetGridSpan())
//				cellToMerge1.getCTTc().getTcPr().unsetGridSpan();
//			cellToMerge1.getCTTc().getTcPr().addNewGridSpan().setVal(BigInteger.valueOf(2)); // Merge two cells horizontally
//
//			// Remove the paragraph in the merged cell
//			for (int i = cellToMerge1.getParagraphs().size() - 1; i > 0; i--) {
//				cellToMerge1.removeParagraph(i);
//			}
//
//			//tableRow9.removeCell(1);
//			XWPFTableCell removed1 = tableRow9.getCell(1);
//		      removed1.getCTTc().newCursor().removeXml();
//		      tableRow9.removeCell(1);
//
//			cell = tableRow9.getCell(0);
//			
//			 XWPFParagraph paragraph = cell.addParagraph();
//			 XWPFRun run = paragraph.createRun();
//			paragraph.setIndentFromLeft(300);
//			//paragraph.setAlignment(ParagraphAlignment.CENTER);
//			String desc = null;
//			Set<PocDes> pdset = pc.getPocdes();
//			for(PocDes pd:pdset) {
//				desc = pd.getPdesc();
//			}
//			run.setText(desc);
//			XWPFParagraph paragraph1 = cell.addParagraph();
//			XWPFRun run1 = paragraph1.createRun();
//			paragraph1.setIndentFromLeft(250);
//			
//			File image = new File("E:\\hi.png");
//			FileInputStream imageData = new FileInputStream(image);
//
//			// Step 5: Retrieving the image file name and image
//			// type
//			int imageType = XWPFDocument.PICTURE_TYPE_JPEG;
//			String imageFileName = image.getName();
//
//			// Step 6: Setting the width and height of the image
//			// in pixels.
//			int width = 430;
//			int height = 270;
//
//			// Step 7: Adding the picture using the addPicture()
//			// method and writing into the document
//			
////			XWPFParagraph paragraph2 = cell.addParagraph();
////			XWPFRun run2 = paragraph2.createRun();
////			//paragraph1.setIndentFromLeft(250);
////			//paragraph2.setSpacingBefore(1000);
//			run1.addPicture(imageData, imageType, imageFileName, Units.toEMU(width), Units.toEMU(height));
//			
//			paragraph1.setSpacingAfter(500);
//			 int pictureIndex = 0;
//	            XWPFPicture picture = run1.getEmbeddedPictures().get(pictureIndex);
//	            org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture ctPicture = picture.getCTPicture();
//
//	            // Adjust margins by modifying the position of the image
//	            CTTransform2D xfrm = ctPicture.getSpPr().getXfrm();
//	            //xfrm.getOff().setX(Units.toEMU(20)); // Left margin
//	            xfrm.getOff().setY(Units.toEMU(10));
//	            paragraph1.setPageBreak(true);
//			 
//	      }
//	      XWPFParagraph paragraph2 = document.createParagraph();
//	      paragraph2.setSpacingBefore(20);
//		     paragraph2.setPageBreak(true);
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      XWPFTableCell cell = null;
			XWPFTableRow tableRow9 = table.getRow(8);

			// First Row

			XWPFTableCell cellToMerge1 = tableRow9.getCell(0); // For example, cell at row 1, column 1
			XWPFTableCell targetCell1 = tableRow9.getCell(1); // For example, cell at row 2, column 2

			if (cellToMerge1.getCTTc().getTcPr() == null)
				cellToMerge1.getCTTc().addNewTcPr();
			if (cellToMerge1.getCTTc().getTcPr().isSetGridSpan())
				cellToMerge1.getCTTc().getTcPr().unsetGridSpan();
			cellToMerge1.getCTTc().getTcPr().addNewGridSpan().setVal(BigInteger.valueOf(2)); // Merge two cells horizontally

			// Remove the paragraph in the merged cell
			for (int i = cellToMerge1.getParagraphs().size() - 1; i > 0; i--) {
				cellToMerge1.removeParagraph(i);
			}

			//tableRow9.removeCell(1);
			XWPFTableCell removed1 = tableRow9.getCell(1);
		      removed1.getCTTc().newCursor().removeXml();
		      tableRow9.removeCell(1);

			cell = tableRow9.getCell(0);
	      for(int j=8;j<8+vfound.getPoc().size();j++) {
	    	  
	    	  
	    	  	Poc pc = plist.get(j-8);
	  			byte[] byt = pc.getPic_byte();
	  			//System.out.println(byt);
	  			conbytoimg(byt);
	  		
	  		
	      
	      	
			
			 XWPFParagraph paragraph = cell.addParagraph();
			 XWPFRun run = paragraph.createRun();
			paragraph.setIndentFromLeft(300);
			//paragraph.setAlignment(ParagraphAlignment.CENTER);
			String desc = null;
			Set<PocDes> pdset = pc.getPocdes();
			for(PocDes pd:pdset) {
				desc = pd.getPdesc();
			}
			run.setText(desc);
			XWPFParagraph paragraph1 = cell.addParagraph();
			XWPFRun run1 = paragraph1.createRun();
			paragraph1.setIndentFromLeft(250);
			
			File image = new File("./src/pic.png");
			FileInputStream imageData = new FileInputStream(image);

			// Step 5: Retrieving the image file name and image
			// type
			int imageType = XWPFDocument.PICTURE_TYPE_JPEG;
			String imageFileName = image.getName();

			// Step 6: Setting the width and height of the image
			// in pixels.
			int width = 430;
			int height = 215;

			// Step 7: Adding the picture using the addPicture()
			// method and writing into the document
			
//			XWPFParagraph paragraph2 = cell.addParagraph();
//			XWPFRun run2 = paragraph2.createRun();
//			//paragraph1.setIndentFromLeft(250);
//			//paragraph2.setSpacingBefore(1000);
			run1.addPicture(imageData, imageType, imageFileName, Units.toEMU(width), Units.toEMU(height));
			
			paragraph1.setSpacingAfter(500);
			 int pictureIndex = 0;
	            XWPFPicture picture = run1.getEmbeddedPictures().get(pictureIndex);
	            org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture ctPicture = picture.getCTPicture();

	            // Adjust margins by modifying the position of the image
	            CTTransform2D xfrm = ctPicture.getSpPr().getXfrm();
	            //xfrm.getOff().setX(Units.toEMU(20)); // Left margin
	            xfrm.getOff().setY(Units.toEMU(10));
	            paragraph1.setPageBreak(true);
			 
	      }
	      XWPFParagraph paragraph2 = document.createParagraph();
	      paragraph2.setSpacingBefore(40);
		     paragraph2.setPageBreak(true);
		     
		     
		     

		     
	      FileOutputStream out;
			try {
				
				out = new FileOutputStream(new File("./src/report2.docx"));
				 
				document.write(out);
			      out.close();
			     // document.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		document.close();
		
	}
	
	
	
}
