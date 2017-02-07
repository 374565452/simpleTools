package com.simple.tools.mp3;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * java录像小程序: 视频文件保存在系统临时目录, 回车全屏播放, ESC退出全屏播放
 * @author dgqjava
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    
    private static final int WIDTH = 200;
    private static final int HEIGHT = 70;
    private static final Color BUTTON_COLOR = new Color(155, 183, 214);
    private static final Color BUTTON_FOCUS_COLOR = new Color(87, 156, 242);
    private static final Color BUTTON_DISABLE_COLOR = new Color(180, 207, 229);
    private static final Font BUTTON_FONT = new Font("华文行楷", Font.BOLD, 15);
    private static final String DEFAULT_FILE_PATH = System.getProperty("java.io.tmpdir");
    
    private static Dimension screenSize;
    
    private final JButton start = new JButton("开始");
    private final JButton pause = new JButton("暂停");
    private final JButton restart = new JButton("继续");
    private final JButton stop = new JButton("完成");
    private final JButton play = new JButton("播放");
    private final JButton close = new JButton("退出");
    
    private String currentFilePath;
    
    public MainFrame() {
        setUndecorated(true);
        setSize(WIDTH, HEIGHT);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        screenSize = tk.getScreenSize();
        setLocation((int) screenSize.getWidth() - WIDTH, (int) (screenSize.getHeight() - tk.getScreenInsets(getGraphicsConfiguration()).bottom - HEIGHT));

        MouseListener buttonMouseListener = new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                if(btn.isEnabled()) {
                    btn.setBackground(BUTTON_FOCUS_COLOR);
                }
            }
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                if(btn.isEnabled()) {
                    btn.setBackground(BUTTON_COLOR);
                }
            }
        };
        start.addMouseListener(buttonMouseListener);
        pause.addMouseListener(buttonMouseListener);
        restart.addMouseListener(buttonMouseListener);
        stop.addMouseListener(buttonMouseListener);
        play.addMouseListener(buttonMouseListener);
        close.addMouseListener(buttonMouseListener);
        
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JButton btn = (JButton) e.getSource();
                if(btn.isEnabled()) {
                    btn.setBackground(BUTTON_COLOR);
                } else {
                    btn.setBackground(BUTTON_DISABLE_COLOR);
                }
            }
        };
        start.addChangeListener(changeListener);
        pause.addChangeListener(changeListener);
        restart.addChangeListener(changeListener);
        stop.addChangeListener(changeListener);
        play.addChangeListener(changeListener);
        
        start.setFont(BUTTON_FONT);
        pause.setFont(BUTTON_FONT);
        restart.setFont(BUTTON_FONT);
        stop.setFont(BUTTON_FONT);
        play.setFont(BUTTON_FONT);
        close.setFont(BUTTON_FONT);
        
        start.setBackground(BUTTON_COLOR);
        pause.setBackground(BUTTON_DISABLE_COLOR);
        restart.setBackground(BUTTON_DISABLE_COLOR);
        stop.setBackground(BUTTON_DISABLE_COLOR);
        play.setBackground(BUTTON_DISABLE_COLOR);
        close.setBackground(BUTTON_COLOR);
        
        pause.setEnabled(false);
        restart.setEnabled(false);
        stop.setEnabled(false);
        play.setEnabled(false);
        
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start.setEnabled(false);
                pause.setEnabled(true);
                stop.setEnabled(true);
                play.setEnabled(false);
                
                currentFilePath = DEFAULT_FILE_PATH + System.currentTimeMillis();
                RecordHelper.start(currentFilePath);
            }
        });
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(false);
                restart.setEnabled(true);
                stop.setEnabled(false);
                
                RecordHelper.pause();
            }
        });
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(true);
                restart.setEnabled(false);
                stop.setEnabled(true);
                
                RecordHelper.restart();
            }
        });
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start.setEnabled(true);
                pause.setEnabled(false);
                stop.setEnabled(false);
                play.setEnabled(true);
                
                RecordHelper.stop();
            }
        });
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                
                new PlayerFrame();
            }
        });
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        setLayout(new GridLayout(2, 3));
        add(start);
        add(pause);
        add(restart);
        add(stop);
        add(play);
        add(close);
        
        setVisible(true);
    }
    
    class PlayerFrame extends JFrame {
        
        private MyVideo mv;
        private final ExecutorService player;
        private volatile BufferedImage lastImage;
        
        public PlayerFrame() {
            player = Executors.newSingleThreadExecutor();
            player.execute(new Runnable() {
                public void run() {
                    mv = new MyVideo(new File(currentFilePath), false, (byte) 0);
                    mv.position(0);
                }
            });
            player.execute(new Runnable() {
                public void run() {
                    for(;;) {
                        Object[] data = mv.nextFrame();
                        if(0 == data.length) {
                            player.shutdown();
                            AudioHelper.flush();
                            dispose();
                            MainFrame.this.setVisible(true);
                            play.setEnabled(true);
                            return;
                        }
                        
                        lastImage = (BufferedImage) data[0];
                        repaint();
                        
                        byte[] audioData = (byte[]) data[1];
                        AudioHelper.writeData(audioData);
                    }
                }
            });
            
            setUndecorated(true);
            setBounds((int) (screenSize.getWidth() / 8), (int) (screenSize.getHeight() / 8), (int) (screenSize.getWidth() / 4 * 3), (int) (screenSize.getHeight() / 4 * 3));
            
            final JPanel canvas = new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    
                    if(null != lastImage) {
                        int w = lastImage.getWidth();
                        int h = lastImage.getHeight();
                        
                        if(w * getHeight() > h * getWidth()) {
                            g.drawImage(lastImage, 0, (int) (getHeight() - getWidth() * 1.0 / w * h) / 2, getWidth(), (int) (getWidth() * 1.0 / w * h), null);
                        } else {
                            g.drawImage(lastImage, (int) (getWidth() - getHeight() * 1.0 / h * w) / 2, 0, (int) (getHeight() * 1.0 / h * w), getHeight(), null);
                        }
                    }
                    
                    Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                    if(new Rectangle(PlayerFrame.this.getLocation().x, PlayerFrame.this.getLocation().y + getHeight() - 70, getWidth(), 50).contains(mouseLocation)) {
                        Color c = g.getColor();
                        g.setColor(new Color(0, 0, 100, 60));
                        g.fill3DRect(0, getHeight() - 70, getWidth(), 50, true);
                        
                        g.setColor(new Color(97, 211, 225, 120));
                        g.fill3DRect(mouseLocation.x - PlayerFrame.this.getX() - 1, getHeight() - 70, 3, 50, true);
                        
                        g.fill3DRect(0, getHeight() - 70, mv.position() * getWidth() / mv.length(), 50, true);
                        
                        g.setColor(c);
                    }
                }
                
            };
            canvas.addMouseMotionListener(new MouseAdapter() {
                public void mouseMoved(MouseEvent e) {
                    repaint();
                }
            });
            canvas.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if(new Rectangle(PlayerFrame.this.getLocation().x, PlayerFrame.this.getLocation().y + getHeight() - 70, getWidth(), 50).contains(e.getLocationOnScreen()) && null != mv) {
                        mv.position((e.getLocationOnScreen().x - PlayerFrame.this.getLocation().x) * mv.length() / getWidth());
                    }
                }
            });
            canvas.setBackground(Color.BLACK);
            add(canvas);

            addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                        PlayerFrame.this.setLocation(0, 0);
                        PlayerFrame.this.setSize(screenSize);
                    } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        PlayerFrame.this.setBounds((int) (screenSize.getWidth() / 8), (int) (screenSize.getHeight() / 8), (int) (screenSize.getWidth() / 4 * 3), (int) (screenSize.getHeight() / 4 * 3));
                    }
                }
            });
            setVisible(true);
        }
    }
    
    public static void main(String[] args) throws Exception {
        new MainFrame();
    }
}

/**
 * 对应一个视频文件, 视频文件数据格式如下:
 * {
 *    文件头信息: 30byte "作者:dgqjava qq群:326337220".getBytes("UTF-8")
 *    帧列表 : [
 *        帧信息 : {
 *            nbyte  图片数据
 *            nbyte  音频数据
 *        }
 *    ]
 *    索引列表 : [
 *        索引信息 : {
 *            1byte   帧标识: 增量01010101|全量10101010
 *            2byte   音频数据压缩大小
 *            3byte   图片数据压缩大小
 *            4byte   帧位置
 *        }
 *    ]
 *    索引列表位置 : 4byte
 *    每秒帧数 : 1byte
 * }
 * @author dgqjava
 *
 */
class MyVideo implements Closeable {
    
    private static final String HEADER = "作者:dgqjava qq群:326337220";
    
    private final byte[] header = new byte[30];
    private final byte rate;
    private final List<FrameIndex> indexList = new ArrayList<FrameIndex>();
    private volatile int frameIndex;
    private volatile BufferedImage last;
    private volatile int[] oldRgbData;
    private long lastFrameTime = -1;
    
    private final boolean isCreate;
    private final RandomAccessFile raf;
    private final FileChannel fileChannel;
    private final MappedByteBuffer mbb;
    
    public MyVideo(File file, boolean isCreate, byte rate) {
        try {
            this.isCreate = isCreate;
            if(isCreate) {
                this.rate = rate;
                mbb = null;
                file.createNewFile();
                raf = new RandomAccessFile(file, "rw");
                fileChannel = raf.getChannel();
                ByteBuffer bb = ByteBuffer.wrap(HEADER.getBytes("UTF-8"));
                while(bb.hasRemaining()) {
                    fileChannel.write(bb);
                }
            } else {
                raf = new RandomAccessFile(file, "r");
                fileChannel = raf.getChannel();
                mbb = fileChannel.map(MapMode.READ_ONLY, 0, fileChannel.size());
                mbb.get(header, 0, header.length);
                if(!HEADER.equals(new String(header, "UTF-8"))) {
                    throw new RuntimeException("文件格式错误");
                }
                
                mbb.position(mbb.capacity() - 5);
                
                byte[] bs = new byte[4];
                mbb.get(bs);
                int indexIndex = ByteBuffer.wrap(bs).getInt();
                this.rate = mbb.get();
                mbb.position(indexIndex);
                
                byte[] indexByte = new byte[10];
                while(mbb.position() < mbb.capacity() - 5) {
                    mbb.get(indexByte);
                    indexList.add(FrameIndex.fromByteArray(indexByte));
                }
            } 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public synchronized void position(int newFrameIndex) {
        try {
            frameIndex = newFrameIndex;
            int lastFullFrameIndex;
            for(lastFullFrameIndex = frameIndex; lastFullFrameIndex >= 0; lastFullFrameIndex--) {
                if(indexList.get(lastFullFrameIndex).isFull) {
                    break;
                }
            }
            
            Frame frame = getFrame(lastFullFrameIndex);
            last = ImageIO.read(new ByteArrayInputStream(ZipHelper.unZip(frame.getPicData()).toByteArray()));
            oldRgbData = ImageHelper.getRgb(last);
            for(int i = lastFullFrameIndex + 1; i <= frameIndex; i++) {
                frame = getFrame(i);
                int[] rgb = ImageHelper.byteArray2RgbArray(ZipHelper.unZip(frame.getPicData()).toByteArray());
                ImageHelper.setRgbArrayChange(last, oldRgbData, rgb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int position() {
        return frameIndex;
    }
    
    public int length() {
        return indexList.size();
    }
    
    public synchronized Object[] nextFrame() {
        try {
            Frame frame = getFrame(++frameIndex);
            if(null == frame) {
                return new Object[]{};
            }
            byte[] audioData = ZipHelper.unZip(frame.getAudioData()).toByteArray();
            int[] rgb = null;
            if(frame.getFlag() == Frame.FULL) {
                last = ImageIO.read(new ByteArrayInputStream(ZipHelper.unZip(frame.getPicData()).toByteArray()));
            } else {
                rgb = ImageHelper.byteArray2RgbArray(ZipHelper.unZip(frame.getPicData()).toByteArray());
            }
            audioData = ZipHelper.unZip(frame.getAudioData()).toByteArray();
            
            if(-1 != lastFrameTime) {
                long period = System.currentTimeMillis() - lastFrameTime;
                if(period < 1000 / rate) {
                    Thread.sleep(1000 / rate - period);
                }
            }
            if(null != rgb) {
                ImageHelper.setRgbArrayChange(last, oldRgbData, rgb);
            }
            lastFrameTime = System.currentTimeMillis();
            
            return new Object[] {last, audioData};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void addFrame(Frame frame) {
        try {
            FrameIndex fi = new FrameIndex(frame.getFlag() == Frame.FULL, frame.getAudioData().length, frame.getPicData().length, (int) fileChannel.position());
            indexList.add(fi);
            write(frame.getPicData());
            write(frame.getAudioData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Frame getFrame(int index) {
        if(index == indexList.size()) {
            return null;
        }
        FrameIndex frameIndex = indexList.get(index);
        byte[] picData = new byte[frameIndex.picDataSize];
        byte[] audioData = new byte[frameIndex.audioDataSize];
        mbb.position(frameIndex.index);
        mbb.get(picData);
        mbb.get(audioData);
        return new Frame(frameIndex.isFull ? Frame.FULL : Frame.INCREMENTAL, picData, audioData);
    }
    
    public void close() {
        try {
            if(isCreate) {
                int indexListIndex = (int) fileChannel.position();
                for(FrameIndex fi : indexList) {
                    write(fi.toByteArray());
                }
                write(ByteBuffer.allocate(4).putInt(indexListIndex).array());
                write(new byte[] {rate});
            }
            fileChannel.force(false);
            raf.close();
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void write(byte[] data) throws IOException {
        ByteBuffer bb = ByteBuffer.wrap(data);
        while(bb.hasRemaining()) {
            fileChannel.write(bb);
        }
    }
    
    static class FrameIndex {
        private final boolean isFull;
        private final int audioDataSize;
        private final int picDataSize;
        private final int index;

        public FrameIndex(boolean isFull, int audioDataSize, int picDataSize, int index) {
            this.isFull = isFull;
            this.audioDataSize = audioDataSize;
            this.picDataSize = picDataSize;
            this.index = index;
        }
        
        public boolean isFull() {
            return isFull;
        }

        public int getAudioDataSize() {
            return audioDataSize;
        }

        public int getPicDataSize() {
            return picDataSize;
        }

        public int getIndex() {
            return index;
        }
        
        public byte[] toByteArray() {
            byte[] result = new byte[10];
            result[0] = (isFull ? Frame.FULL : Frame.INCREMENTAL);
            ByteBuffer.allocate(4).putInt(audioDataSize).array();
            System.arraycopy(ByteBuffer.allocate(4).putInt(audioDataSize).array(), 2, result, 1, 2);
            System.arraycopy(ByteBuffer.allocate(4).putInt(picDataSize).array(), 1, result, 3, 3);
            System.arraycopy(ByteBuffer.allocate(4).putInt(index).array(), 0, result, 6, 4);
            return result;
        }
        
        public static FrameIndex fromByteArray(byte[] bs) {
            boolean isFull = (Frame.FULL == bs[0]);
            byte[] temp = new byte[8];
            System.arraycopy(bs, 1, temp, 6, 2);
            int audioDataSize = (int) ByteBuffer.wrap(temp).getLong();
            System.arraycopy(bs, 3, temp, 5, 3);
            int picDataSize = (int) ByteBuffer.wrap(temp).getLong();
            System.arraycopy(bs, 6, temp, 4, 4);
            int index = (int) ByteBuffer.wrap(temp).getLong();
            
            return new FrameIndex(isFull, audioDataSize, picDataSize, index);
        }
    }
    
    static class Frame {
        public static final Frame TERMINATE_FRAME = new Frame((byte) 0, null, null);
        public static final byte FULL = 0;
        public static final byte INCREMENTAL = 1;
        
        private final byte flag;
        private final byte[] picData;
        private final byte[] audioData;
        
        public Frame(byte flag, byte[] picData, byte[] audioData) {
            this.flag = flag;
            this.picData = picData;
            this.audioData = audioData;
        }
        
        public byte getFlag() {
            return flag;
        }
        
        public byte[] getPicData() {
            return picData;
        }

        public byte[] getAudioData() {
            return audioData;
        }
    }
}

class RecordHelper {
    
    private static final byte RECORD_RATE = 5;
    private static final int DEFAULT_FULL_PIC_RATE = 80;
    private static final int FULL_PIC_PERIOD = 60;
    
    private static final Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    private static ScheduledExecutorService screenCapture;
    private static ScheduledExecutorService audioProcesser;
    private static final ExecutorService ioProcesser = Executors.newSingleThreadExecutor();
    private static final ExecutorService dataProcesser = Executors.newSingleThreadExecutor();
    
    private static final BlockingQueue<Object[]> screenQueue = new LinkedBlockingQueue<Object[]>();
    private static final BlockingQueue<byte[]> audioQueue = new LinkedBlockingQueue<byte[]>();
    private static final BlockingQueue<MyVideo.Frame> frameQueue = new LinkedBlockingQueue<MyVideo.Frame>();
    
    private static volatile boolean generateFull = false;
    
    public static void start(final String filePath) {
        generateFull = true;
        restart();

        dataProcesser.execute(new Runnable() {
            public void run() {
                try {
                    double lastFullPicTime = -1;
                    int[] latestRgb = null;
                    
                    for(;;) {
                        byte[] picData;
                        byte flag;
                        Object[] imageData = screenQueue.take();
                        byte[] audioData = audioQueue.take();
                        
                        if(generateFull) {
                            latestRgb = ImageHelper.getRgb((BufferedImage) imageData[1]);
                            picData = ZipHelper.zip(ImageHelper.image2Stream((BufferedImage) imageData[1]).toByteArray()).toByteArray();
                            flag = MyVideo.Frame.FULL;
                            lastFullPicTime = (Long) imageData[0];
                            frameQueue.put(new MyVideo.Frame(flag, picData, ZipHelper.zip(audioData).toByteArray()));
                            generateFull = false;
                            continue;
                        }
                        
                        int[] temp = latestRgb.clone();
                        double rate = DEFAULT_FULL_PIC_RATE - ((((Long) imageData[0]) - lastFullPicTime) * DEFAULT_FULL_PIC_RATE / 1000 / FULL_PIC_PERIOD);
                        byte[] changeData = ImageHelper.getByteArrayChange(temp, (BufferedImage) imageData[1], rate);
                        if(null != changeData) {
                            latestRgb = temp;
                            picData = ZipHelper.zip(changeData).toByteArray();
                            flag = MyVideo.Frame.INCREMENTAL;
                        } else {
                            latestRgb = ImageHelper.getRgb((BufferedImage) imageData[1]);
                            picData = ZipHelper.zip(ImageHelper.image2Stream((BufferedImage) imageData[1]).toByteArray()).toByteArray();
                            flag = MyVideo.Frame.FULL;
                            lastFullPicTime = (Long) imageData[0];
                        }
                        frameQueue.put(new MyVideo.Frame(flag, picData, ZipHelper.zip(audioData).toByteArray()));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        ioProcesser.execute(new Runnable() {
            public void run() {
                MyVideo video = new MyVideo(new File(filePath), true, RECORD_RATE);
                try {
                    for(;;) {
                        MyVideo.Frame frame = frameQueue.take();
                        if(frame == MyVideo.Frame.TERMINATE_FRAME) {
                            return;
                        }
                        video.addFrame(frame);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    video.close();
                }
            }
        });
    }
    
    public static void pause() {
        screenCapture.shutdownNow();
        audioProcesser.shutdownNow();
    }

    public static void restart() {
        screenCapture = Executors.newSingleThreadScheduledExecutor();
        audioProcesser = Executors.newSingleThreadScheduledExecutor();
        
        screenCapture.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    screenQueue.put(new Object[] {System.currentTimeMillis(), ImageHelper.getScreen(rectangle)});
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, 0, 1000 / RECORD_RATE, TimeUnit.MILLISECONDS);
        
        final int bufferSize = AudioHelper.getBufferSize();
        byte[] data = new byte[bufferSize];
        AudioHelper.readData(data);
        audioProcesser.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    byte[] data = new byte[bufferSize];
                    int count = AudioHelper.readData(data);
                    audioQueue.put(Arrays.copyOf(data, count));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, 0, 1000 / RECORD_RATE, TimeUnit.MILLISECONDS);
    }
    
    public static void stop() {
        try {
            pause();
            frameQueue.put(MyVideo.Frame.TERMINATE_FRAME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

final class ImageHelper {
    
    private static final Robot robot;
    private static final int DEFAULT_RGB = (255 << 24) | (0 << 16) | (0 << 8) | (0 << 0);
    private static final String DEFAULT_FORMAT_NAME = "BMP";
    
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static BufferedImage getScreen(Rectangle rectangle) {
        return robot.createScreenCapture(rectangle);
    }
    
    /**
     * 获取图片的rgb数组
     * @param images
     * @return
     */
    public static int[] getRgb(BufferedImage images) {
        int w = images.getWidth();
        int h = images.getHeight();

        int[] result = new int[w * h];
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                result[i * w + j] = images.getRGB(j, i);
            }
        }
        
        return result;
    }
    
    /**
     * 获取图片像素对比后差异的rgb数组, 并将新图片的变化部分更新到原图片的rgb数组中
     * @param oldRgbData 对比的原图片的rgb数组
     * @param currentImage 新图片
     * @return
     */
    public static int[] getRgbChange(int[] oldRgbData, BufferedImage currentImage) {
        int w = currentImage.getWidth();
        int h = currentImage.getHeight();

        int[] result = new int[w * h];
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                int index = i * w + j;
                int oldRgb = oldRgbData[index];
                int newRgb = currentImage.getRGB(j, i);
                
                int rgb;
                if(oldRgb != newRgb) {
                    rgb = (DEFAULT_RGB == newRgb ? oldRgb : newRgb);
                    result[index] = rgb;
                } else {
                    rgb = DEFAULT_RGB;
                    result[index] = rgb;
                }
                oldRgbData[index] = rgb;
            }
        }
        
        return result;
    }
    
    /**
     * 获取图片像素对比后差异的rgb数组并压缩生成byte数组, 并将新图片的变化部分更新到原图片的rgb数组中
     * @param oldRgbData
     * @param currentImage
     * @param allowPercent 当像素的变化比例超过这个值时放弃生成byte数组, 返回null
     * @return
     */
    public static byte[] getByteArrayChange(int[] oldRgbData, BufferedImage currentImage, double allowPercent) {
        try {
            int h = currentImage.getHeight();
            int w = currentImage.getWidth();
    
            int total = w * h;
            int changeCount = 0;
            ByteBuffer buffer = ByteBuffer.allocate(4);
            int samePointCount = 0;
            List<Integer> chunkCountList = new ArrayList<Integer>();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    int index = i * w + j;
                    int oldRgb = oldRgbData[index];
                    int newRgb = currentImage.getRGB(j, i);
                    
                    int rgb;
                    if(oldRgb != newRgb) {
                        if(changeCount++ * 100D / total >= allowPercent) {
                            return null;
                        }
                        oldRgbData[index] = newRgb;
                        rgb = (DEFAULT_RGB == newRgb ? oldRgb : newRgb);
                    } else {
                        rgb = DEFAULT_RGB;
                    }
                    
                    if(DEFAULT_RGB == rgb) {
                        if(samePointCount >= 0) {
                            samePointCount++;
                        } else {
                            chunkCountList.add(samePointCount);
                            samePointCount = 1;
                        }
                    } else {
                        if(samePointCount <= 0) {
                            samePointCount--;
                        } else {
                            chunkCountList.add(samePointCount);
                            samePointCount = -1;
                        }
                        buffer.clear();
                        baos.write(buffer.putInt(rgb).array());
                    }
                }
            }
            chunkCountList.add(samePointCount);
            
            byte[] rgbData = baos.toByteArray();
            byte[] result = new byte[(chunkCountList.size() + 1) * 4 + rgbData.length];
            buffer.clear();
            int index = 0;
            System.arraycopy(buffer.putInt(chunkCountList.size()).array(), 0, result, index, 4);
            for(int chunkSize : chunkCountList) {
                buffer.clear();
                System.arraycopy(buffer.putInt(chunkSize).array(), 0, result, index += 4, 4);
            }
            System.arraycopy(rgbData, 0, result, index + 4, rgbData.length);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 设置图片像素对比后差异的rgb数组, 并将变化部分更新到原图片的rgb数组中
     * @param lastImage
     * @param oldRgbData
     * @param rgbChangeData
     */
    public static void setRgbArrayChange(BufferedImage lastImage, int[] oldRgbData, int[] rgbChangeData) {
        int h = lastImage.getHeight();
        int w = lastImage.getWidth();

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                int index = i * w + j;
                int newRgb = rgbChangeData[index];
                
                if(DEFAULT_RGB != newRgb) {
                    int oldRgb = oldRgbData[index];
                    int rgb;
                    if(oldRgb != newRgb) {
                        rgb = newRgb;
                        lastImage.setRGB(j, i, rgb);
                    } else {
                        rgb = DEFAULT_RGB;
                        lastImage.setRGB(j, i, rgb);
                    }
                    oldRgbData[index] = rgb;
                }
            }
        }
    }
    
    public static void setRgbArray(BufferedImage image, int[] rgbChangeData) {
        int h = image.getHeight();
        int w = image.getWidth();

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                int newRgb = rgbChangeData[i * w + j];
                
                if(DEFAULT_RGB != newRgb) {
                    image.setRGB(j, i, newRgb);
                }
            }
        }
    }
    
    public static ByteArrayOutputStream image2Stream(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, DEFAULT_FORMAT_NAME, baos);
            return baos;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 压缩后的byte数组还原为rgb数组
     * @param data
     * @return
     */
    public static int[] byteArray2RgbArray(byte[] data) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.clear();
        buffer.put(new byte[] {data[0], data[1], data[2], data[3]});
        buffer.flip();
        int chunkCount = buffer.getInt();
        List<Integer> chunkCountList = new ArrayList<Integer>(chunkCount);
        int index = 4;
        int rgbCount = 0;
        for(int i = 0; i < chunkCount; i++) {
            buffer.clear();
            buffer.put((new byte[] {data[index + 0], data[index + 1], data[index + 2], data[index + 3]}));
            buffer.flip();
            
            int chunkSize = buffer.getInt();
            chunkCountList.add(chunkSize);
            rgbCount += Math.abs(chunkSize);
            index += 4;
        }
        
        int[] rgbData = new int[rgbCount];
        int rgbDataIndex = 0;
        for(int chunkSize : chunkCountList) {
            if(chunkSize > 0) {
                for(int j = 0; j < chunkSize; j++) {
                    rgbData[rgbDataIndex++] = DEFAULT_RGB;
                }
            } else {
                for(int j = 0; j > chunkSize; j--) {
                    buffer.clear();
                    buffer.put(new byte[] {data[index + 0], data[index + 1], data[index + 2], data[index + 3]});
                    buffer.flip();
                    rgbData[rgbDataIndex++] = buffer.getInt();
                    index += 4;
                }
            }
        }
        
        return rgbData;
    }
}

final class AudioHelper {
    
    private static final float RATE = 8000;
    private static final int SAMPLE_SIZE_IN_BITS = 8;
    private static final int CHANNELS = 1;
    private static final AudioFormat DEFAULT_AUDIO_FORMAT = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, SAMPLE_SIZE_IN_BITS / 8 * CHANNELS, RATE, false);
    
    private static SourceDataLine sourceDataLine;
    private static TargetDataLine targetDataLine;
    
    static {
        try {
            DataLine.Info targetInfo = new DataLine.Info(TargetDataLine.class, DEFAULT_AUDIO_FORMAT);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
            targetDataLine.open(DEFAULT_AUDIO_FORMAT);
            targetDataLine.start();
            
            DataLine.Info sourceInfo = new DataLine.Info(SourceDataLine.class, DEFAULT_AUDIO_FORMAT);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
            sourceDataLine.open(DEFAULT_AUDIO_FORMAT);
            sourceDataLine.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int getBufferSize() {
        return targetDataLine.getBufferSize();
    }
    
    public static int readData(byte[] bs) {
        return targetDataLine.read(bs, 0, targetDataLine.available());
    }
    
    public static void writeData(byte[] bs) {
        sourceDataLine.write(bs, 0, bs.length);
    }
    
    public static void flush() {
        sourceDataLine.flush();
    }
}

final class ZipHelper {

    public static ByteArrayOutputStream zip(byte[] data) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ZipOutputStream zip = new ZipOutputStream(baos);
            ZipEntry entry = new ZipEntry("zip");
            entry.setSize(data.length);
            zip.putNextEntry(entry);
            zip.write(data);
            zip.closeEntry();
            zip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos;
    }

    public static ByteArrayOutputStream unZip(byte[] data) {
        ByteArrayOutputStream baos = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ZipInputStream zip = new ZipInputStream(bis);
            if (zip.getNextEntry() != null) {
                byte[] buf = new byte[1024];
                int num;
                baos = new ByteArrayOutputStream();
                while ((num = zip.read(buf, 0, buf.length)) != -1) {
                    baos.write(buf, 0, num);
                }
                baos.flush();
                baos.close();
            }
            zip.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos;
    }
}
