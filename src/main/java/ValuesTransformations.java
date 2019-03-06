import java.io.*;
import java.util.*;

public class ValuesTransformations {
    public String transformedFileName;
    private FileWriter fileWriter;
    private BufferedWriter writer;
    private List<Emg> transformedValues;
    private List<Emg> movingAverageValues;

    public ValuesTransformations(String oldFileName) throws IOException {
        this.transformedFileName = "transformed-" + oldFileName;
        this.fileWriter = new FileWriter( transformedFileName);
        transformedValues = new ArrayList<Emg>();
        movingAverageValues = new ArrayList<Emg>();
    }

    public void Rescaling(String fileName) throws FileNotFoundException {
        writer = new BufferedWriter(this.fileWriter);

        //Get scanner instance
        Scanner scanner = new Scanner(new File("emgData1526375118339.csv"));

        //Set the delimiter used in file
        scanner.useDelimiter(",|\\n");

        // Skip header line
        scanner.nextLine();

        //I am just printing them
        while (scanner.hasNext())
        {
            transformedValues.add(new Emg(
                    scanner.nextLong(),
                    scanner.nextByte() + 128,
                    scanner.nextByte() + 128,
                    scanner.nextByte() + 128,
                    scanner.nextByte() + 128,
                    scanner.nextByte() + 128,
                    scanner.nextByte() + 128,
                    scanner.nextByte() + 128,
                    scanner.nextByte() + 128,
                    scanner.nextBoolean()));

        }
        //Do not forget to close the scanner
        scanner.close();
    }

    public void calculateMovingAverage(int samples, int overlap) throws IOException {
        FileWriter fileWriter = new FileWriter("avgValues.csv");
        BufferedWriter writer = new BufferedWriter(fileWriter);
        int end = transformedValues.size() - samples;
        int count1 = 0,
                count2 = 0,
                count3 = 0,
                count4 = 0,
                count5 = 0,
                count6 = 0,
                count7 = 0,
                count8 = 0;
        long timestamp = 0;
        boolean isFatigued = false;
        int index = 0;
        for(int i = 0; i < end; i += overlap){
            for(int j = i; j <i + samples; j++){
                count1 += this.transformedValues.get(j).getEmg1();
                count2 += this.transformedValues.get(j).getEmg2();
                count3 += this.transformedValues.get(j).getEmg3();
                count4 += this.transformedValues.get(j).getEmg4();
                count5 += this.transformedValues.get(j).getEmg5();
                count6 += this.transformedValues.get(j).getEmg6();
                count7 += this.transformedValues.get(j).getEmg7();
                count8 += this.transformedValues.get(j).getEmg8();
                timestamp = this.transformedValues.get(j).getTimestamp();
                if (this.transformedValues.get(j).isFatigued()){
                    isFatigued = true;
                }
            }
            count1 /= samples;
            count2 /= samples;
            count3 /= samples;
            count4 /= samples;
            count5 /= samples;
            count6 /= samples;
            count7 /= samples;
            count8 /= samples;
            movingAverageValues.add(new Emg(timestamp,count1, count2, count3, count4,
                    count5, count6, count7, count8, isFatigued));
            isFatigued  = false;
            writer.write(movingAverageValues.get(index++).getEmgSignal() + "\n");
        }
        writer.close();
        fileWriter.close();
    }

    public void writeTransformedValue(int value) throws IOException {
        writer.write(value);
    }

}
