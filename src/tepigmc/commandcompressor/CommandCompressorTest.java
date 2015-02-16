package tepigmc.commandcompressor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CommandCompressorTest extends junit.framework.TestCase {
  String commands1;
  String commands2;
  String commands3;
  String output1;
  String output2;
  String output3;

  @Before
  public void setUp() {
    commands1 = "/summon Pig ~ ~ ~\n/give @a dirt 64\n/gamemode 1 @p";
    commands2 = "\n\n\n/say hi\n\n";
    commands3 = "\n\n\n/say hi\n/say bye\n";
    output1 = "summon MinecartCommandBlock ~ ~1 ~ {Riding:{id:MinecartCommandBlock,Riding:{id:MinecartCommandBlock,Riding:{id:MinecartCommandBlock,Riding:{id:FallingSand,TileID:157,Time:1},Command:/summon Pig ~ ~ ~},Command:/give @a dirt 64},Command:/gamemode 1 @p},Command:setblock ~ ~-1 ~ 10 7}";
    output2 = "/say hi";
    output3 = "summon MinecartCommandBlock ~ ~1 ~ {Riding:{id:MinecartCommandBlock,Riding:{id:MinecartCommandBlock,Riding:{id:FallingSand,TileID:157,Time:1},Command:/say hi},Command:/say bye},Command:setblock ~ ~-1 ~ 10 7}";
  }

  @After
  public void tearDown() {
    
  }

  @Test
  public void testConvert1() {
    assertEquals(output1, CommandCompressor.convert(commands1));
  }

  @Test
  public void testConvert2() {
    assertEquals(output2, CommandCompressor.convert(commands2));
  }

  @Test
  public void testConvert3() {
    assertEquals(output3, CommandCompressor.convert(commands3));
  }
}