// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: common.proto

package org.artempopov.serverFirst.proto;

public final class Common {
  private Common() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code Shape}
   */
  public enum Shape
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>CIRCLE = 0;</code>
     */
    CIRCLE(0, 0),
    /**
     * <code>SQUARE = 1;</code>
     */
    SQUARE(1, 1),
    /**
     * <code>TRIANGLE = 2;</code>
     */
    TRIANGLE(2, 2),
    ;

    /**
     * <code>CIRCLE = 0;</code>
     */
    public static final int CIRCLE_VALUE = 0;
    /**
     * <code>SQUARE = 1;</code>
     */
    public static final int SQUARE_VALUE = 1;
    /**
     * <code>TRIANGLE = 2;</code>
     */
    public static final int TRIANGLE_VALUE = 2;


    public final int getNumber() { return value; }

    public static Shape valueOf(int value) {
      switch (value) {
        case 0: return CIRCLE;
        case 1: return SQUARE;
        case 2: return TRIANGLE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Shape>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<Shape>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Shape>() {
            public Shape findValueByNumber(int number) {
              return Shape.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return org.artempopov.serverFirst.proto.Common.getDescriptor().getEnumTypes().get(0);
    }

    private static final Shape[] VALUES = values();

    public static Shape valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private Shape(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:Shape)
  }

  /**
   * Protobuf enum {@code Color}
   */
  public enum Color
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>RED = 0;</code>
     */
    RED(0, 0),
    /**
     * <code>GREEN = 1;</code>
     */
    GREEN(1, 1),
    /**
     * <code>BLUE = 2;</code>
     */
    BLUE(2, 2),
    /**
     * <code>BLACK = 3;</code>
     */
    BLACK(3, 3),
    /**
     * <code>ORANGE = 4;</code>
     */
    ORANGE(4, 4),
    /**
     * <code>VIOLET = 5;</code>
     */
    VIOLET(5, 5),
    ;

    /**
     * <code>RED = 0;</code>
     */
    public static final int RED_VALUE = 0;
    /**
     * <code>GREEN = 1;</code>
     */
    public static final int GREEN_VALUE = 1;
    /**
     * <code>BLUE = 2;</code>
     */
    public static final int BLUE_VALUE = 2;
    /**
     * <code>BLACK = 3;</code>
     */
    public static final int BLACK_VALUE = 3;
    /**
     * <code>ORANGE = 4;</code>
     */
    public static final int ORANGE_VALUE = 4;
    /**
     * <code>VIOLET = 5;</code>
     */
    public static final int VIOLET_VALUE = 5;


    public final int getNumber() { return value; }

    public static Color valueOf(int value) {
      switch (value) {
        case 0: return RED;
        case 1: return GREEN;
        case 2: return BLUE;
        case 3: return BLACK;
        case 4: return ORANGE;
        case 5: return VIOLET;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Color>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<Color>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Color>() {
            public Color findValueByNumber(int number) {
              return Color.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return org.artempopov.serverFirst.proto.Common.getDescriptor().getEnumTypes().get(1);
    }

    private static final Color[] VALUES = values();

    public static Color valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private Color(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:Color)
  }

  public interface PositionOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 x = 1;
    /**
     * <code>required int32 x = 1;</code>
     */
    boolean hasX();
    /**
     * <code>required int32 x = 1;</code>
     */
    int getX();

    // required int32 y = 2;
    /**
     * <code>required int32 y = 2;</code>
     */
    boolean hasY();
    /**
     * <code>required int32 y = 2;</code>
     */
    int getY();
  }
  /**
   * Protobuf type {@code Position}
   */
  public static final class Position extends
      com.google.protobuf.GeneratedMessage
      implements PositionOrBuilder {
    // Use Position.newBuilder() to construct.
    private Position(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Position(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Position defaultInstance;
    public static Position getDefaultInstance() {
      return defaultInstance;
    }

    public Position getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Position(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              x_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              y_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.artempopov.serverFirst.proto.Common.internal_static_Position_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.artempopov.serverFirst.proto.Common.internal_static_Position_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.artempopov.serverFirst.proto.Common.Position.class, org.artempopov.serverFirst.proto.Common.Position.Builder.class);
    }

    public static com.google.protobuf.Parser<Position> PARSER =
        new com.google.protobuf.AbstractParser<Position>() {
      public Position parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Position(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<Position> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 x = 1;
    public static final int X_FIELD_NUMBER = 1;
    private int x_;
    /**
     * <code>required int32 x = 1;</code>
     */
    public boolean hasX() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 x = 1;</code>
     */
    public int getX() {
      return x_;
    }

    // required int32 y = 2;
    public static final int Y_FIELD_NUMBER = 2;
    private int y_;
    /**
     * <code>required int32 y = 2;</code>
     */
    public boolean hasY() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 y = 2;</code>
     */
    public int getY() {
      return y_;
    }

    private void initFields() {
      x_ = 0;
      y_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasX()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasY()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, x_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, y_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, x_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, y_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static org.artempopov.serverFirst.proto.Common.Position parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.artempopov.serverFirst.proto.Common.Position parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.artempopov.serverFirst.proto.Common.Position parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.artempopov.serverFirst.proto.Common.Position parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.artempopov.serverFirst.proto.Common.Position parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.artempopov.serverFirst.proto.Common.Position parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static org.artempopov.serverFirst.proto.Common.Position parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static org.artempopov.serverFirst.proto.Common.Position parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static org.artempopov.serverFirst.proto.Common.Position parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.artempopov.serverFirst.proto.Common.Position parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(org.artempopov.serverFirst.proto.Common.Position prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Position}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements org.artempopov.serverFirst.proto.Common.PositionOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.artempopov.serverFirst.proto.Common.internal_static_Position_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.artempopov.serverFirst.proto.Common.internal_static_Position_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.artempopov.serverFirst.proto.Common.Position.class, org.artempopov.serverFirst.proto.Common.Position.Builder.class);
      }

      // Construct using org.artempopov.serverFirst.proto.Common.Position.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        x_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        y_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.artempopov.serverFirst.proto.Common.internal_static_Position_descriptor;
      }

      public org.artempopov.serverFirst.proto.Common.Position getDefaultInstanceForType() {
        return org.artempopov.serverFirst.proto.Common.Position.getDefaultInstance();
      }

      public org.artempopov.serverFirst.proto.Common.Position build() {
        org.artempopov.serverFirst.proto.Common.Position result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public org.artempopov.serverFirst.proto.Common.Position buildPartial() {
        org.artempopov.serverFirst.proto.Common.Position result = new org.artempopov.serverFirst.proto.Common.Position(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.x_ = x_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.y_ = y_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.artempopov.serverFirst.proto.Common.Position) {
          return mergeFrom((org.artempopov.serverFirst.proto.Common.Position)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.artempopov.serverFirst.proto.Common.Position other) {
        if (other == org.artempopov.serverFirst.proto.Common.Position.getDefaultInstance()) return this;
        if (other.hasX()) {
          setX(other.getX());
        }
        if (other.hasY()) {
          setY(other.getY());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasX()) {
          
          return false;
        }
        if (!hasY()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.artempopov.serverFirst.proto.Common.Position parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.artempopov.serverFirst.proto.Common.Position) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 x = 1;
      private int x_ ;
      /**
       * <code>required int32 x = 1;</code>
       */
      public boolean hasX() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 x = 1;</code>
       */
      public int getX() {
        return x_;
      }
      /**
       * <code>required int32 x = 1;</code>
       */
      public Builder setX(int value) {
        bitField0_ |= 0x00000001;
        x_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 x = 1;</code>
       */
      public Builder clearX() {
        bitField0_ = (bitField0_ & ~0x00000001);
        x_ = 0;
        onChanged();
        return this;
      }

      // required int32 y = 2;
      private int y_ ;
      /**
       * <code>required int32 y = 2;</code>
       */
      public boolean hasY() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 y = 2;</code>
       */
      public int getY() {
        return y_;
      }
      /**
       * <code>required int32 y = 2;</code>
       */
      public Builder setY(int value) {
        bitField0_ |= 0x00000002;
        y_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 y = 2;</code>
       */
      public Builder clearY() {
        bitField0_ = (bitField0_ & ~0x00000002);
        y_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:Position)
    }

    static {
      defaultInstance = new Position(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:Position)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_Position_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Position_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014common.proto\" \n\010Position\022\t\n\001x\030\001 \002(\005\022\t\n" +
      "\001y\030\002 \002(\005*-\n\005Shape\022\n\n\006CIRCLE\020\000\022\n\n\006SQUARE\020" +
      "\001\022\014\n\010TRIANGLE\020\002*H\n\005Color\022\007\n\003RED\020\000\022\t\n\005GRE" +
      "EN\020\001\022\010\n\004BLUE\020\002\022\t\n\005BLACK\020\003\022\n\n\006ORANGE\020\004\022\n\n" +
      "\006VIOLET\020\005B\"\n org.artempopov.serverFirst." +
      "proto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_Position_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_Position_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_Position_descriptor,
              new java.lang.String[] { "X", "Y", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}