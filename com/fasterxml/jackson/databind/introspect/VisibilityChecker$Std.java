package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker.1;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

@JsonAutoDetect(creatorVisibility = Visibility.ANY, fieldVisibility = Visibility.PUBLIC_ONLY, getterVisibility = Visibility.PUBLIC_ONLY, isGetterVisibility = Visibility.PUBLIC_ONLY, setterVisibility = Visibility.ANY)
public class VisibilityChecker$Std implements VisibilityChecker<VisibilityChecker$Std>, Serializable {
    protected static final VisibilityChecker$Std DEFAULT;
    private static final long serialVersionUID = -7073939237187922755L;
    protected final Visibility _creatorMinLevel;
    protected final Visibility _fieldMinLevel;
    protected final Visibility _getterMinLevel;
    protected final Visibility _isGetterMinLevel;
    protected final Visibility _setterMinLevel;

    static {
        DEFAULT = new VisibilityChecker$Std((JsonAutoDetect) VisibilityChecker$Std.class.getAnnotation(JsonAutoDetect.class));
    }

    public static VisibilityChecker$Std defaultInstance() {
        return DEFAULT;
    }

    public VisibilityChecker$Std(JsonAutoDetect jsonAutoDetect) {
        this._getterMinLevel = jsonAutoDetect.getterVisibility();
        this._isGetterMinLevel = jsonAutoDetect.isGetterVisibility();
        this._setterMinLevel = jsonAutoDetect.setterVisibility();
        this._creatorMinLevel = jsonAutoDetect.creatorVisibility();
        this._fieldMinLevel = jsonAutoDetect.fieldVisibility();
    }

    public VisibilityChecker$Std(Visibility visibility, Visibility visibility2, Visibility visibility3, Visibility visibility4, Visibility visibility5) {
        this._getterMinLevel = visibility;
        this._isGetterMinLevel = visibility2;
        this._setterMinLevel = visibility3;
        this._creatorMinLevel = visibility4;
        this._fieldMinLevel = visibility5;
    }

    public VisibilityChecker$Std(Visibility visibility) {
        if (visibility == Visibility.DEFAULT) {
            this._getterMinLevel = DEFAULT._getterMinLevel;
            this._isGetterMinLevel = DEFAULT._isGetterMinLevel;
            this._setterMinLevel = DEFAULT._setterMinLevel;
            this._creatorMinLevel = DEFAULT._creatorMinLevel;
            this._fieldMinLevel = DEFAULT._fieldMinLevel;
            return;
        }
        this._getterMinLevel = visibility;
        this._isGetterMinLevel = visibility;
        this._setterMinLevel = visibility;
        this._creatorMinLevel = visibility;
        this._fieldMinLevel = visibility;
    }

    public VisibilityChecker$Std with(JsonAutoDetect jsonAutoDetect) {
        if (jsonAutoDetect != null) {
            return withGetterVisibility(jsonAutoDetect.getterVisibility()).withIsGetterVisibility(jsonAutoDetect.isGetterVisibility()).withSetterVisibility(jsonAutoDetect.setterVisibility()).withCreatorVisibility(jsonAutoDetect.creatorVisibility()).withFieldVisibility(jsonAutoDetect.fieldVisibility());
        }
        return this;
    }

    public VisibilityChecker$Std with(Visibility visibility) {
        if (visibility == Visibility.DEFAULT) {
            return DEFAULT;
        }
        return new VisibilityChecker$Std(visibility);
    }

    public VisibilityChecker$Std withVisibility(PropertyAccessor propertyAccessor, Visibility visibility) {
        switch (1.a[propertyAccessor.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                return withGetterVisibility(visibility);
            case Task.NETWORK_STATE_ANY /*2*/:
                return withSetterVisibility(visibility);
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                return withCreatorVisibility(visibility);
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                return withFieldVisibility(visibility);
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                return withIsGetterVisibility(visibility);
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                return with(visibility);
            default:
                return this;
        }
    }

    public VisibilityChecker$Std withGetterVisibility(Visibility visibility) {
        Visibility visibility2;
        if (visibility == Visibility.DEFAULT) {
            visibility2 = DEFAULT._getterMinLevel;
        } else {
            visibility2 = visibility;
        }
        return this._getterMinLevel == visibility2 ? this : new VisibilityChecker$Std(visibility2, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    public VisibilityChecker$Std withIsGetterVisibility(Visibility visibility) {
        Visibility visibility2;
        if (visibility == Visibility.DEFAULT) {
            visibility2 = DEFAULT._isGetterMinLevel;
        } else {
            visibility2 = visibility;
        }
        return this._isGetterMinLevel == visibility2 ? this : new VisibilityChecker$Std(this._getterMinLevel, visibility2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
    }

    public VisibilityChecker$Std withSetterVisibility(Visibility visibility) {
        Visibility visibility2;
        if (visibility == Visibility.DEFAULT) {
            visibility2 = DEFAULT._setterMinLevel;
        } else {
            visibility2 = visibility;
        }
        return this._setterMinLevel == visibility2 ? this : new VisibilityChecker$Std(this._getterMinLevel, this._isGetterMinLevel, visibility2, this._creatorMinLevel, this._fieldMinLevel);
    }

    public VisibilityChecker$Std withCreatorVisibility(Visibility visibility) {
        Visibility visibility2;
        if (visibility == Visibility.DEFAULT) {
            visibility2 = DEFAULT._creatorMinLevel;
        } else {
            visibility2 = visibility;
        }
        return this._creatorMinLevel == visibility2 ? this : new VisibilityChecker$Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, visibility2, this._fieldMinLevel);
    }

    public VisibilityChecker$Std withFieldVisibility(Visibility visibility) {
        Visibility visibility2;
        if (visibility == Visibility.DEFAULT) {
            visibility2 = DEFAULT._fieldMinLevel;
        } else {
            visibility2 = visibility;
        }
        return this._fieldMinLevel == visibility2 ? this : new VisibilityChecker$Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, visibility2);
    }

    public boolean isCreatorVisible(Member member) {
        return this._creatorMinLevel.isVisible(member);
    }

    public boolean isCreatorVisible(AnnotatedMember annotatedMember) {
        return isCreatorVisible(annotatedMember.getMember());
    }

    public boolean isFieldVisible(Field field) {
        return this._fieldMinLevel.isVisible(field);
    }

    public boolean isFieldVisible(AnnotatedField annotatedField) {
        return isFieldVisible(annotatedField.getAnnotated());
    }

    public boolean isGetterVisible(Method method) {
        return this._getterMinLevel.isVisible(method);
    }

    public boolean isGetterVisible(AnnotatedMethod annotatedMethod) {
        return isGetterVisible(annotatedMethod.getAnnotated());
    }

    public boolean isIsGetterVisible(Method method) {
        return this._isGetterMinLevel.isVisible(method);
    }

    public boolean isIsGetterVisible(AnnotatedMethod annotatedMethod) {
        return isIsGetterVisible(annotatedMethod.getAnnotated());
    }

    public boolean isSetterVisible(Method method) {
        return this._setterMinLevel.isVisible(method);
    }

    public boolean isSetterVisible(AnnotatedMethod annotatedMethod) {
        return isSetterVisible(annotatedMethod.getAnnotated());
    }

    public String toString() {
        return "[Visibility:" + " getter: " + this._getterMinLevel + ", isGetter: " + this._isGetterMinLevel + ", setter: " + this._setterMinLevel + ", creator: " + this._creatorMinLevel + ", field: " + this._fieldMinLevel + "]";
    }
}
