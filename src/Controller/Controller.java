package Controller;
import Compression.Factory.CompressionFactory;
import Compression.ICompression;
import IO.FileIO;

public class Controller {
    private final CompressionFactory compressionFactoryInstance = CompressionFactory.getCompressionFactoryInstance();
    private final String[] algorithmsList = compressionFactoryInstance.getAvailableCompressionAlgorithms();
    private final FileIO fileIO = new FileIO();
    private ICompression compressionAlgorithm;

    public String[] getAlgorithmsList(){
        return algorithmsList;
    }
    public void setFileReadPath(String fileReadPath){
        fileIO.setReadFilePath(fileReadPath);
    }
    public void setFileWritePath(String fileWritePath){
        fileIO.setWriteFilePath(fileWritePath);
    }
    public void setCompressionAlgorithm(int compressionAlgorithmNumber){
        compressionAlgorithm = compressionFactoryInstance.createCompression(algorithmsList[compressionAlgorithmNumber]);
    }
    public String compress(){
        String data = fileIO.readData();
        String compressedData = compressionAlgorithm.compress(data);
        fileIO.print(compressedData);
        return compressedData;
    }
    public String decompress(){
        String data = fileIO.readData();
        String deCompressedData = compressionAlgorithm.decompress(data);
        fileIO.print(deCompressedData);
        return deCompressedData;
    }
}
