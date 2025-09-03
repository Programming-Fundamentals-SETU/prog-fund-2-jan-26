package models;

import utils.Utilities;

import java.util.Objects;

public class InstructionBooklet {

    //TODO The number of pages (int) is between 1 and 80 (both inclusive).  Default is 1.
    private int numberOfPages = 1;

    //TODO The file name (String) of the booklet in the system is entered by the user.
    //     Default value is "".
    //     When creating the booklet, truncate the name to 20 characters.
    //     When updating an existing booklet, only update the name if it is 20 characters or less.
    private String fileName = "";

    //TODO Add the constructor, InstructionBooklet(int, String), that adheres to the above validation rules
    public InstructionBooklet(int numberOfPages, String fileName) {
        setNumberOfPages(numberOfPages);
        this.fileName = Utilities.truncateString(fileName,20);
    }

    //TODO Add a getter and setter for each field, that adheres to the above validation rules
    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        if (Utilities.validRange(numberOfPages, 1, 80)) {
            this.numberOfPages = numberOfPages;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        if (Utilities.validStringlength(fileName, 20)) {
            this.fileName = fileName;
        }
    }

    //TODO Add a generated equals method.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstructionBooklet that)) return false;

        if (numberOfPages != that.numberOfPages) return false;
        return Objects.equals(fileName, that.fileName);
    }

    //TODO The toString should return the string in this format:
    //      legobooklet1.pdf (5 pages)  OR
    //      legobooklet2.pdf (1 page)   OR
    //      legobooklet3.pdf (0 pages)
    //  NOTE: .pdf is added to the actual file name if the user hasn't added it themselves.
    //  NOTE: "pages" is added to the number of pages when it is not equal 1, "page" otherwise.
    @Override
    public String toString() {
        String extension = fileName
                .substring(fileName.length()-4)
                .toLowerCase()
                .contains(".pdf")? "" : ".pdf";

        String pages = (numberOfPages == 1) ? " page" : " pages";

        return fileName + extension + " (" + numberOfPages + pages + ")";
    }
}