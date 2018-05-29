// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: common.proto

package org.artempopov.ServerFirst.proto;

public final class Common {
  private Common() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code Shape}
   */
  public enum Shape
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>CIRCLE = 0;</code>
     */
    CIRCLE(0),
    /**
     * <code>SQUARE = 1;</code>
     */
    SQUARE(1),
    /**
     * <code>TRIANGLE = 2;</code>
     */
    TRIANGLE(2),
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


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Shape valueOf(int value) {
      return forNumber(value);
    }

    public static Shape forNumber(int value) {
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
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Shape> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Shape>() {
            public Shape findValueByNumber(int number) {
              return Shape.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return org.artempopov.ServerFirst.proto.Common.getDescriptor().getEnumTypes().get(0);
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

    private final int value;

    private Shape(int value) {
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
    RED(0),
    /**
     * <code>GREEN = 1;</code>
     */
    GREEN(1),
    /**
     * <code>BLUE = 2;</code>
     */
    BLUE(2),
    /**
     * <code>BLACK = 3;</code>
     */
    BLACK(3),
    /**
     * <code>ORANGE = 4;</code>
     */
    ORANGE(4),
    /**
     * <code>VIOLET = 5;</code>
     */
    VIOLET(5),
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


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Color valueOf(int value) {
      return forNumber(value);
    }

    public static Color forNumber(int value) {
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
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Color> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Color>() {
            public Color findValueByNumber(int number) {
              return Color.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return org.artempopov.ServerFirst.proto.Common.getDescriptor().getEnumTypes().get(1);
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

    private final int value;

    private Color(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:Color)
  }

  public interface PositionOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Position)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 x = 1;</code>
     */
    boolean hasX();
    /**
     * <code>required int32 x = 1;</code>
     */
    int getX();

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
  public  static final class Position extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Position)
      PositionOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Position.newBuilder() to construct.
    private Position(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Position() {
      x_ = 0;
      y_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Position(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
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
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
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
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.artempopov.ServerFirst.proto.Common.internal_static_Position_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.artempopov.ServerFirst.proto.Common.internal_static_Position_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.artempopov.ServerFirst.proto.Common.Position.class, org.artempopov.ServerFirst.proto.Common.Position.Builder.class);
    }

    private int bitField0_;
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

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

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
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, x_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, y_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
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
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof org.artempopov.ServerFirst.proto.Common.Position)) {
        return super.equals(obj);
      }
      org.artempopov.ServerFirst.proto.Common.Position other = (org.artempopov.ServerFirst.proto.Common.Position) obj;

      boolean result = true;
      result = result && (hasX() == other.hasX());
      if (hasX()) {
        result = result && (getX()
            == other.getX());
      }
      result = result && (hasY() == other.hasY());
      if (hasY()) {
        result = result && (getY()
            == other.getY());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasX()) {
        hash = (37 * hash) + X_FIELD_NUMBER;
        hash = (53 * hash) + getX();
      }
      if (hasY()) {
        hash = (37 * hash) + Y_FIELD_NUMBER;
        hash = (53 * hash) + getY();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static org.artempopov.ServerFirst.proto.Common.Position parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.artempopov.ServerFirst.proto.Common.Position parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(org.artempopov.ServerFirst.proto.Common.Position prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Position}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Position)
        org.artempopov.ServerFirst.proto.Common.PositionOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.artempopov.ServerFirst.proto.Common.internal_static_Position_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.artempopov.ServerFirst.proto.Common.internal_static_Position_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.artempopov.ServerFirst.proto.Common.Position.class, org.artempopov.ServerFirst.proto.Common.Position.Builder.class);
      }

      // Construct using org.artempopov.ServerFirst.proto.Common.Position.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        x_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        y_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.artempopov.ServerFirst.proto.Common.internal_static_Position_descriptor;
      }

      public org.artempopov.ServerFirst.proto.Common.Position getDefaultInstanceForType() {
        return org.artempopov.ServerFirst.proto.Common.Position.getDefaultInstance();
      }

      public org.artempopov.ServerFirst.proto.Common.Position build() {
        org.artempopov.ServerFirst.proto.Common.Position result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public org.artempopov.ServerFirst.proto.Common.Position buildPartial() {
        org.artempopov.ServerFirst.proto.Common.Position result = new org.artempopov.ServerFirst.proto.Common.Position(this);
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

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.artempopov.ServerFirst.proto.Common.Position) {
          return mergeFrom((org.artempopov.ServerFirst.proto.Common.Position)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.artempopov.ServerFirst.proto.Common.Position other) {
        if (other == org.artempopov.ServerFirst.proto.Common.Position.getDefaultInstance()) return this;
        if (other.hasX()) {
          setX(other.getX());
        }
        if (other.hasY()) {
          setY(other.getY());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
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
        org.artempopov.ServerFirst.proto.Common.Position parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.artempopov.ServerFirst.proto.Common.Position) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

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
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Position)
    }

    // @@protoc_insertion_point(class_scope:Position)
    private static final org.artempopov.ServerFirst.proto.Common.Position DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new org.artempopov.ServerFirst.proto.Common.Position();
    }

    public static org.artempopov.ServerFirst.proto.Common.Position getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<Position>
        PARSER = new com.google.protobuf.AbstractParser<Position>() {
      public Position parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Position(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Position> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Position> getParserForType() {
      return PARSER;
    }

    public org.artempopov.ServerFirst.proto.Common.Position getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Position_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Position_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014common.proto\" \n\010Position\022\t\n\001x\030\001 \002(\005\022\t\n" +
      "\001y\030\002 \002(\005*-\n\005Shape\022\n\n\006CIRCLE\020\000\022\n\n\006SQUARE\020" +
      "\001\022\014\n\010TRIANGLE\020\002*H\n\005Color\022\007\n\003RED\020\000\022\t\n\005GRE" +
      "EN\020\001\022\010\n\004BLUE\020\002\022\t\n\005BLACK\020\003\022\n\n\006ORANGE\020\004\022\n\n" +
      "\006VIOLET\020\005B\"\n org.artempopov.ServerFirst." +
      "proto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_Position_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Position_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Position_descriptor,
        new java.lang.String[] { "X", "Y", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
