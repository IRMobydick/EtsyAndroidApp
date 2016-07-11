package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.PropertyName;

public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable<POJOPropertyBuilder> {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected a<AnnotatedParameter> _ctorParameters;
    protected a<AnnotatedField> _fields;
    protected final boolean _forSerialization;
    protected a<AnnotatedMethod> _getters;
    protected final String _internalName;
    protected final String _name;
    protected a<AnnotatedMethod> _setters;

    public POJOPropertyBuilder(String str, AnnotationIntrospector annotationIntrospector, boolean z) {
        this._internalName = str;
        this._name = str;
        this._annotationIntrospector = annotationIntrospector;
        this._forSerialization = z;
    }

    public POJOPropertyBuilder(POJOPropertyBuilder pOJOPropertyBuilder, String str) {
        this._internalName = pOJOPropertyBuilder._internalName;
        this._name = str;
        this._annotationIntrospector = pOJOPropertyBuilder._annotationIntrospector;
        this._fields = pOJOPropertyBuilder._fields;
        this._ctorParameters = pOJOPropertyBuilder._ctorParameters;
        this._getters = pOJOPropertyBuilder._getters;
        this._setters = pOJOPropertyBuilder._setters;
        this._forSerialization = pOJOPropertyBuilder._forSerialization;
    }

    public POJOPropertyBuilder withName(String str) {
        return new POJOPropertyBuilder(this, str);
    }

    public int compareTo(POJOPropertyBuilder pOJOPropertyBuilder) {
        if (this._ctorParameters != null) {
            if (pOJOPropertyBuilder._ctorParameters == null) {
                return -1;
            }
        } else if (pOJOPropertyBuilder._ctorParameters != null) {
            return 1;
        }
        return getName().compareTo(pOJOPropertyBuilder.getName());
    }

    public String getName() {
        return this._name;
    }

    public String getInternalName() {
        return this._internalName;
    }

    public PropertyName getWrapperName() {
        AnnotatedMember primaryMember = getPrimaryMember();
        return (primaryMember == null || this._annotationIntrospector == null) ? null : this._annotationIntrospector.findWrapperName(primaryMember);
    }

    public boolean isExplicitlyIncluded() {
        return _anyExplicitNames(this._fields) || _anyExplicitNames(this._getters) || _anyExplicitNames(this._setters) || _anyExplicitNames(this._ctorParameters);
    }

    public boolean hasGetter() {
        return this._getters != null;
    }

    public boolean hasSetter() {
        return this._setters != null;
    }

    public boolean hasField() {
        return this._fields != null;
    }

    public boolean hasConstructorParameter() {
        return this._ctorParameters != null;
    }

    public boolean couldSerialize() {
        return (this._getters == null && this._fields == null) ? false : true;
    }

    public AnnotatedMethod getGetter() {
        if (this._getters == null) {
            return null;
        }
        AnnotatedMethod annotatedMethod = (AnnotatedMethod) this._getters.a;
        a aVar = this._getters.b;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        while (aVar != null) {
            annotatedMethod = (AnnotatedMethod) aVar.a;
            Class declaringClass = annotatedMethod2.getDeclaringClass();
            Class declaringClass2 = annotatedMethod.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (!declaringClass.isAssignableFrom(declaringClass2)) {
                    if (declaringClass2.isAssignableFrom(declaringClass)) {
                        annotatedMethod = annotatedMethod2;
                    }
                }
                aVar = aVar.b;
                annotatedMethod2 = annotatedMethod;
            }
            throw new IllegalArgumentException("Conflicting getter definitions for property \"" + getName() + "\": " + annotatedMethod2.getFullName() + " vs " + annotatedMethod.getFullName());
        }
        return annotatedMethod2;
    }

    public AnnotatedMethod getSetter() {
        if (this._setters == null) {
            return null;
        }
        AnnotatedMethod annotatedMethod = (AnnotatedMethod) this._setters.a;
        a aVar = this._setters.b;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        while (aVar != null) {
            annotatedMethod = (AnnotatedMethod) aVar.a;
            Class declaringClass = annotatedMethod2.getDeclaringClass();
            Class declaringClass2 = annotatedMethod.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (!declaringClass.isAssignableFrom(declaringClass2)) {
                    if (declaringClass2.isAssignableFrom(declaringClass)) {
                        annotatedMethod = annotatedMethod2;
                    }
                }
                aVar = aVar.b;
                annotatedMethod2 = annotatedMethod;
            }
            throw new IllegalArgumentException("Conflicting setter definitions for property \"" + getName() + "\": " + annotatedMethod2.getFullName() + " vs " + annotatedMethod.getFullName());
        }
        return annotatedMethod2;
    }

    public AnnotatedField getField() {
        if (this._fields == null) {
            return null;
        }
        AnnotatedField annotatedField = (AnnotatedField) this._fields.a;
        a aVar = this._fields.b;
        AnnotatedField annotatedField2 = annotatedField;
        while (aVar != null) {
            annotatedField = (AnnotatedField) aVar.a;
            Class declaringClass = annotatedField2.getDeclaringClass();
            Class declaringClass2 = annotatedField.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (!declaringClass.isAssignableFrom(declaringClass2)) {
                    if (declaringClass2.isAssignableFrom(declaringClass)) {
                        annotatedField = annotatedField2;
                    }
                }
                aVar = aVar.b;
                annotatedField2 = annotatedField;
            }
            throw new IllegalArgumentException("Multiple fields representing property \"" + getName() + "\": " + annotatedField2.getFullName() + " vs " + annotatedField.getFullName());
        }
        return annotatedField2;
    }

    public AnnotatedParameter getConstructorParameter() {
        if (this._ctorParameters == null) {
            return null;
        }
        a aVar = this._ctorParameters;
        while (!(((AnnotatedParameter) aVar.a).getOwner() instanceof AnnotatedConstructor)) {
            a aVar2 = aVar.b;
            if (aVar2 == null) {
                return (AnnotatedParameter) this._ctorParameters.a;
            }
            aVar = aVar2;
        }
        return (AnnotatedParameter) aVar.a;
    }

    public AnnotatedMember getAccessor() {
        AnnotatedMember getter = getGetter();
        if (getter == null) {
            return getField();
        }
        return getter;
    }

    public AnnotatedMember getMutator() {
        AnnotatedMember constructorParameter = getConstructorParameter();
        if (constructorParameter != null) {
            return constructorParameter;
        }
        constructorParameter = getSetter();
        if (constructorParameter == null) {
            return getField();
        }
        return constructorParameter;
    }

    public AnnotatedMember getPrimaryMember() {
        if (this._forSerialization) {
            return getAccessor();
        }
        return getMutator();
    }

    public Class<?>[] findViews() {
        return (Class[]) fromMemberAnnotations(new 1(this));
    }

    public ReferenceProperty findReferenceType() {
        return (ReferenceProperty) fromMemberAnnotations(new 2(this));
    }

    public boolean isTypeId() {
        Boolean bool = (Boolean) fromMemberAnnotations(new 3(this));
        return bool != null && bool.booleanValue();
    }

    public boolean isRequired() {
        Boolean bool = (Boolean) fromMemberAnnotations(new 4(this));
        return bool != null && bool.booleanValue();
    }

    public ObjectIdInfo findObjectIdInfo() {
        return (ObjectIdInfo) fromMemberAnnotations(new 5(this));
    }

    public void addField(AnnotatedField annotatedField, String str, boolean z, boolean z2) {
        this._fields = new a(annotatedField, this._fields, str, z, z2);
    }

    public void addCtor(AnnotatedParameter annotatedParameter, String str, boolean z, boolean z2) {
        this._ctorParameters = new a(annotatedParameter, this._ctorParameters, str, z, z2);
    }

    public void addGetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        this._getters = new a(annotatedMethod, this._getters, str, z, z2);
    }

    public void addSetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        this._setters = new a(annotatedMethod, this._setters, str, z, z2);
    }

    public void addAll(POJOPropertyBuilder pOJOPropertyBuilder) {
        this._fields = merge(this._fields, pOJOPropertyBuilder._fields);
        this._ctorParameters = merge(this._ctorParameters, pOJOPropertyBuilder._ctorParameters);
        this._getters = merge(this._getters, pOJOPropertyBuilder._getters);
        this._setters = merge(this._setters, pOJOPropertyBuilder._setters);
    }

    private static <T> a<T> merge(a<T> aVar, a<T> aVar2) {
        if (aVar == null) {
            return aVar2;
        }
        if (aVar2 == null) {
            return aVar;
        }
        return a.a(aVar, aVar2);
    }

    public void removeIgnored() {
        this._fields = _removeIgnored(this._fields);
        this._getters = _removeIgnored(this._getters);
        this._setters = _removeIgnored(this._setters);
        this._ctorParameters = _removeIgnored(this._ctorParameters);
    }

    @Deprecated
    public void removeNonVisible() {
        removeNonVisible(false);
    }

    public void removeNonVisible(boolean z) {
        this._getters = _removeNonVisible(this._getters);
        this._ctorParameters = _removeNonVisible(this._ctorParameters);
        if (z || this._getters == null) {
            this._fields = _removeNonVisible(this._fields);
            this._setters = _removeNonVisible(this._setters);
        }
    }

    public void trimByVisibility() {
        this._fields = _trimByVisibility(this._fields);
        this._getters = _trimByVisibility(this._getters);
        this._setters = _trimByVisibility(this._setters);
        this._ctorParameters = _trimByVisibility(this._ctorParameters);
    }

    public void mergeAnnotations(boolean z) {
        if (z) {
            if (this._getters != null) {
                this._getters = this._getters.a(((AnnotatedMethod) this._getters.a).withAnnotations(_mergeAnnotations(0, this._getters, this._fields, this._ctorParameters, this._setters)));
            } else if (this._fields != null) {
                this._fields = this._fields.a(((AnnotatedField) this._fields.a).withAnnotations(_mergeAnnotations(0, this._fields, this._ctorParameters, this._setters)));
            }
        } else if (this._ctorParameters != null) {
            this._ctorParameters = this._ctorParameters.a(((AnnotatedParameter) this._ctorParameters.a).withAnnotations(_mergeAnnotations(0, this._ctorParameters, this._setters, this._fields, this._getters)));
        } else if (this._setters != null) {
            this._setters = this._setters.a(((AnnotatedMethod) this._setters.a).withAnnotations(_mergeAnnotations(0, this._setters, this._fields, this._getters)));
        } else if (this._fields != null) {
            this._fields = this._fields.a(((AnnotatedField) this._fields.a).withAnnotations(_mergeAnnotations(0, this._fields, this._getters)));
        }
    }

    private AnnotationMap _mergeAnnotations(int i, a<? extends AnnotatedMember>... aVarArr) {
        AnnotationMap allAnnotations = ((AnnotatedMember) aVarArr[i].a).getAllAnnotations();
        for (int i2 = i + 1; i2 < aVarArr.length; i2++) {
            if (aVarArr[i2] != null) {
                return AnnotationMap.merge(allAnnotations, _mergeAnnotations(i2, aVarArr));
            }
        }
        return allAnnotations;
    }

    private <T> a<T> _removeIgnored(a<T> aVar) {
        return aVar == null ? aVar : aVar.a();
    }

    private <T> a<T> _removeNonVisible(a<T> aVar) {
        return aVar == null ? aVar : aVar.b();
    }

    private <T> a<T> _trimByVisibility(a<T> aVar) {
        return aVar == null ? aVar : aVar.c();
    }

    private <T> boolean _anyExplicitNames(a<T> aVar) {
        while (aVar != null) {
            if (aVar.c != null && aVar.c.length() > 0) {
                return true;
            }
            aVar = aVar.b;
        }
        return false;
    }

    public boolean anyVisible() {
        return _anyVisible(this._fields) || _anyVisible(this._getters) || _anyVisible(this._setters) || _anyVisible(this._ctorParameters);
    }

    private <T> boolean _anyVisible(a<T> aVar) {
        while (aVar != null) {
            if (aVar.d) {
                return true;
            }
            aVar = aVar.b;
        }
        return false;
    }

    public boolean anyIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    private <T> boolean _anyIgnorals(a<T> aVar) {
        while (aVar != null) {
            if (aVar.e) {
                return true;
            }
            aVar = aVar.b;
        }
        return false;
    }

    public String findNewName() {
        a findRenamed = findRenamed(this._ctorParameters, findRenamed(this._setters, findRenamed(this._getters, findRenamed(this._fields, null))));
        if (findRenamed == null) {
            return null;
        }
        return findRenamed.c;
    }

    private a<? extends AnnotatedMember> findRenamed(a<? extends AnnotatedMember> aVar, a<? extends AnnotatedMember> aVar2) {
        a<? extends AnnotatedMember> aVar3 = aVar2;
        for (a<? extends AnnotatedMember> aVar4 = aVar; aVar4 != null; aVar4 = aVar4.b) {
            String str = aVar4.c;
            if (!(str == null || str.equals(this._name))) {
                if (aVar3 == null) {
                    aVar3 = aVar4;
                } else if (!str.equals(aVar3.c)) {
                    throw new IllegalStateException("Conflicting property name definitions: '" + aVar3.c + "' (for " + aVar3.a + ") vs '" + aVar4.c + "' (for " + aVar4.a + ")");
                }
            }
        }
        return aVar3;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[Property '").append(this._name).append("'; ctors: ").append(this._ctorParameters).append(", field(s): ").append(this._fields).append(", getter(s): ").append(this._getters).append(", setter(s): ").append(this._setters);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    protected <T> T fromMemberAnnotations(b<T> bVar) {
        T t = null;
        if (this._annotationIntrospector == null) {
            return null;
        }
        if (!this._forSerialization) {
            if (this._ctorParameters != null) {
                t = bVar.b((AnnotatedMember) this._ctorParameters.a);
            }
            if (t == null && this._setters != null) {
                t = bVar.b((AnnotatedMember) this._setters.a);
            }
        } else if (this._getters != null) {
            t = bVar.b((AnnotatedMember) this._getters.a);
        }
        if (t != null || this._fields == null) {
            return t;
        }
        return bVar.b((AnnotatedMember) this._fields.a);
    }
}
