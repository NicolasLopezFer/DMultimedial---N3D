package proyectoeditorvideo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IPixelFormat.Type;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;

public class VideoThumbnailsExample {

    public static final double SECONDS_BETWEEN_FRAMES = 0.009;

    private static final String inputFilename = "metro.mp4";
    private static final String outputFilePrefix = "snapshots/mysnapshot";

    // The video stream index, used to ensure we display frames from one and
    // only one video stream from the media container.
    private static int mVideoStreamIndex = -1;

    // Time of last frame write
    private static long mLastPtsWrite = Global.NO_PTS;
    public static IMediaWriter writer;

    public static final long MICRO_SECONDS_BETWEEN_FRAMES
            = (long) (Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);

    public static long startTime;
    public static String imagePathS;

    public static int ubiLayoutX;

    public static int ubiLayoutY;

    public static void agregarImagen(String inputVideo, String pathOutput, String imagePath, double ubicaX, double ubicaY) {

        ubiLayoutX = (int) ubicaX;
        ubiLayoutY = (int) ubicaY;
        //AudioExtractor.convert(inputFilename, "metro.mp3");
        imagePathS = imagePath;

        writer = ToolFactory.makeWriter(pathOutput);
        IMediaReader mediaReader = ToolFactory.makeReader(inputVideo);

        // stipulate that we want BufferedImages created in BGR 24bit color space
        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

        mediaReader.addListener(new ImageSnapListener());

        IContainer containerVideo = IContainer.make();

        // check files are readable
        if (containerVideo.open(inputFilename, IContainer.Type.READ, null) < 0) {
            throw new IllegalArgumentException("Cant find " + inputVideo);
        }

        IStreamCoder coderVideo = containerVideo.getStream(0).getStreamCoder();
        if (coderVideo.open(null, null) < 0) {
            throw new RuntimeException("Cant open video coder");
        }

        int width = coderVideo.getWidth();
        int height = coderVideo.getHeight();
        writer.addVideoStream(0, 0, width, height);
        startTime = System.nanoTime();
        // read out the contents of the media file and
        // dispatch events to the attached listener
        while (mediaReader.readPacket() == null) ;
        writer.close();
        coderVideo.close();
        containerVideo.close();
        mediaReader.close();
        

    }

    public static void agregarMusica(String inputVideo, String pathOutput, String songPath) {
        MergeVideoAudio.ejecutar(inputVideo, songPath, pathOutput);
    }

    private static class ImageSnapListener extends MediaListenerAdapter {

        public void onVideoPicture(IVideoPictureEvent event) {

            if (event.getStreamIndex() != mVideoStreamIndex) {
                // if the selected video stream id is not yet set, go ahead an
                // select this lucky video stream
                if (mVideoStreamIndex == -1) {
                    mVideoStreamIndex = event.getStreamIndex();
                } // no need to show frames from this video stream
                else {
                    return;
                }
            }
            if (event.getMediaData().isComplete()) {
                BufferedImage image = event.getImage();
                try {
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    ImageIO.write(image, "png", output);
                    BufferedImage insertImage = ImageIO.read(new File(imagePathS));
                    ByteArrayOutputStream insertOutput = new ByteArrayOutputStream();
                    ImageIO.write(insertImage, "png", insertOutput);

                    int auxi = 0;
                    int auxj = 0;

                    for (int i = (ubiLayoutX * image.getWidth()) / 500; i < ((ubiLayoutX * image.getWidth()) / 500) + insertImage.getWidth(); i++) {
                        for (int j = (ubiLayoutY * image.getHeight()) / 300; j < ((ubiLayoutY * image.getHeight()) / 300) + insertImage.getHeight(); j++) {
                            if (auxj < insertImage.getHeight() && auxi < insertImage.getWidth()) {
                                int rgb = insertImage.getRGB(auxi, auxj);
                                image.setRGB(i, j, rgb);
                            }
                            auxj++;

                        }
                        auxi++;
                        auxj = 0;
                    }
                    writer.encodeVideo(0, image, event.getTimeStamp(), TimeUnit.MICROSECONDS);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

    }

}
