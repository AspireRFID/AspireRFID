
package org.fosstrak.ale.wsdl.aleac.epcglobal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.fosstrak.ale.xsd.ale.epcglobal.ACClientIdentity;
import org.fosstrak.ale.xsd.ale.epcglobal.ACPermission;
import org.fosstrak.ale.xsd.ale.epcglobal.ACRole;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.fosstrak.ale.wsdl.aleac.epcglobal package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RemovePermissions_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "RemovePermissions");
    private final static QName _UndefinePermission_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "UndefinePermission");
    private final static QName _AddPermissions_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "AddPermissions");
    private final static QName _GetVendorVersionResult_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetVendorVersionResult");
    private final static QName _DuplicateClientIdentityException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "DuplicateClientIdentityException");
    private final static QName _NoSuchClientIdentityException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "NoSuchClientIdentityException");
    private final static QName _DefineRole_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "DefineRole");
    private final static QName _ImplementationException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "ImplementationException");
    private final static QName _GetClientIdentity_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetClientIdentity");
    private final static QName _GetClientIdentityResult_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetClientIdentityResult");
    private final static QName _GetClientIdentityNamesResult_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetClientIdentityNamesResult");
    private final static QName _GetClientIdentityNames_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetClientIdentityNames");
    private final static QName _AddRoles_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "AddRoles");
    private final static QName _GetPermissionNames_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetPermissionNames");
    private final static QName _DuplicateRoleException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "DuplicateRoleException");
    private final static QName _NoSuchRoleException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "NoSuchRoleException");
    private final static QName _NoSuchPermissionException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "NoSuchPermissionException");
    private final static QName _GetRole_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetRole");
    private final static QName _ClientIdentityValidationException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "ClientIdentityValidationException");
    private final static QName _UndefineClientIdentity_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "UndefineClientIdentity");
    private final static QName _GetVendorVersion_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetVendorVersion");
    private final static QName _UpdateClientIdentity_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "UpdateClientIdentity");
    private final static QName _GetClientPermissionNames_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetClientPermissionNames");
    private final static QName _PermissionValidationException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "PermissionValidationException");
    private final static QName _UpdateRole_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "UpdateRole");
    private final static QName _UnsupportedOperationException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "UnsupportedOperationException");
    private final static QName _GetStandardVersionResult_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetStandardVersionResult");
    private final static QName _RemoveRoles_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "RemoveRoles");
    private final static QName _GetRoleNamesResult_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetRoleNamesResult");
    private final static QName _GetStandardVersion_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetStandardVersion");
    private final static QName _DuplicatePermissionException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "DuplicatePermissionException");
    private final static QName _ALEException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "ALEException");
    private final static QName _DefineClientIdentity_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "DefineClientIdentity");
    private final static QName _GetClientPermissionNamesResult_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetClientPermissionNamesResult");
    private final static QName _SecurityException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "SecurityException");
    private final static QName _UndefineRole_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "UndefineRole");
    private final static QName _UpdatePermission_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "UpdatePermission");
    private final static QName _GetPermission_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetPermission");
    private final static QName _SetPermissions_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "SetPermissions");
    private final static QName _RoleValidationException_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "RoleValidationException");
    private final static QName _GetSupportedOperations_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetSupportedOperations");
    private final static QName _GetRoleNames_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetRoleNames");
    private final static QName _GetSupportedOperationsResult_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetSupportedOperationsResult");
    private final static QName _GetPermissionNamesResult_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetPermissionNamesResult");
    private final static QName _GetPermissionResult_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetPermissionResult");
    private final static QName _SetRoles_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "SetRoles");
    private final static QName _DefinePermission_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "DefinePermission");
    private final static QName _GetRoleResult_QNAME = new QName("urn:epcglobal:aleac:wsdl:1", "GetRoleResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.fosstrak.ale.wsdl.aleac.epcglobal
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UndefineRole }
     * 
     */
    public UndefineRole createUndefineRole() {
        return new UndefineRole();
    }

    /**
     * Create an instance of {@link UpdateClientIdentity }
     * 
     */
    public UpdateClientIdentity createUpdateClientIdentity() {
        return new UpdateClientIdentity();
    }

    /**
     * Create an instance of {@link DefineClientIdentityResult }
     * 
     */
    public DefineClientIdentityResult createDefineClientIdentityResult() {
        return new DefineClientIdentityResult();
    }

    /**
     * Create an instance of {@link EmptyParms }
     * 
     */
    public EmptyParms createEmptyParms() {
        return new EmptyParms();
    }

    /**
     * Create an instance of {@link PermissionValidationException }
     * 
     */
    public PermissionValidationException createPermissionValidationException() {
        return new PermissionValidationException();
    }

    /**
     * Create an instance of {@link SetRoles.RoleNames }
     * 
     */
    public SetRoles.RoleNames createSetRolesRoleNames() {
        return new SetRoles.RoleNames();
    }

    /**
     * Create an instance of {@link DefineRoleResult }
     * 
     */
    public DefineRoleResult createDefineRoleResult() {
        return new DefineRoleResult();
    }

    /**
     * Create an instance of {@link UnsupportedOperationException }
     * 
     */
    public UnsupportedOperationException createUnsupportedOperationException() {
        return new UnsupportedOperationException();
    }

    /**
     * Create an instance of {@link AddRoles.RoleNames }
     * 
     */
    public AddRoles.RoleNames createAddRolesRoleNames() {
        return new AddRoles.RoleNames();
    }

    /**
     * Create an instance of {@link DefinePermissionResult }
     * 
     */
    public DefinePermissionResult createDefinePermissionResult() {
        return new DefinePermissionResult();
    }

    /**
     * Create an instance of {@link RemovePermissions }
     * 
     */
    public RemovePermissions createRemovePermissions() {
        return new RemovePermissions();
    }

    /**
     * Create an instance of {@link UpdateRoleResult }
     * 
     */
    public UpdateRoleResult createUpdateRoleResult() {
        return new UpdateRoleResult();
    }

    /**
     * Create an instance of {@link RemoveRoles.RoleNames }
     * 
     */
    public RemoveRoles.RoleNames createRemoveRolesRoleNames() {
        return new RemoveRoles.RoleNames();
    }

    /**
     * Create an instance of {@link RemoveRoles }
     * 
     */
    public RemoveRoles createRemoveRoles() {
        return new RemoveRoles();
    }

    /**
     * Create an instance of {@link SetRolesResult }
     * 
     */
    public SetRolesResult createSetRolesResult() {
        return new SetRolesResult();
    }

    /**
     * Create an instance of {@link RoleValidationException }
     * 
     */
    public RoleValidationException createRoleValidationException() {
        return new RoleValidationException();
    }

    /**
     * Create an instance of {@link UpdateRole }
     * 
     */
    public UpdateRole createUpdateRole() {
        return new UpdateRole();
    }

    /**
     * Create an instance of {@link DefinePermission }
     * 
     */
    public DefinePermission createDefinePermission() {
        return new DefinePermission();
    }

    /**
     * Create an instance of {@link SecurityException }
     * 
     */
    public SecurityException createSecurityException() {
        return new SecurityException();
    }

    /**
     * Create an instance of {@link UndefineClientIdentityResult }
     * 
     */
    public UndefineClientIdentityResult createUndefineClientIdentityResult() {
        return new UndefineClientIdentityResult();
    }

    /**
     * Create an instance of {@link UndefineRoleResult }
     * 
     */
    public UndefineRoleResult createUndefineRoleResult() {
        return new UndefineRoleResult();
    }

    /**
     * Create an instance of {@link GetClientPermissionNames }
     * 
     */
    public GetClientPermissionNames createGetClientPermissionNames() {
        return new GetClientPermissionNames();
    }

    /**
     * Create an instance of {@link RemovePermissionsResult }
     * 
     */
    public RemovePermissionsResult createRemovePermissionsResult() {
        return new RemovePermissionsResult();
    }

    /**
     * Create an instance of {@link GetPermission }
     * 
     */
    public GetPermission createGetPermission() {
        return new GetPermission();
    }

    /**
     * Create an instance of {@link UpdatePermissionResult }
     * 
     */
    public UpdatePermissionResult createUpdatePermissionResult() {
        return new UpdatePermissionResult();
    }

    /**
     * Create an instance of {@link AddRoles }
     * 
     */
    public AddRoles createAddRoles() {
        return new AddRoles();
    }

    /**
     * Create an instance of {@link RemovePermissions.PermissionNames }
     * 
     */
    public RemovePermissions.PermissionNames createRemovePermissionsPermissionNames() {
        return new RemovePermissions.PermissionNames();
    }

    /**
     * Create an instance of {@link DefineClientIdentity }
     * 
     */
    public DefineClientIdentity createDefineClientIdentity() {
        return new DefineClientIdentity();
    }

    /**
     * Create an instance of {@link SetPermissions.PermissionNames }
     * 
     */
    public SetPermissions.PermissionNames createSetPermissionsPermissionNames() {
        return new SetPermissions.PermissionNames();
    }

    /**
     * Create an instance of {@link AddRolesResult }
     * 
     */
    public AddRolesResult createAddRolesResult() {
        return new AddRolesResult();
    }

    /**
     * Create an instance of {@link ALEException }
     * 
     */
    public ALEException createALEException() {
        return new ALEException();
    }

    /**
     * Create an instance of {@link UndefineClientIdentity }
     * 
     */
    public UndefineClientIdentity createUndefineClientIdentity() {
        return new UndefineClientIdentity();
    }

    /**
     * Create an instance of {@link SetPermissions }
     * 
     */
    public SetPermissions createSetPermissions() {
        return new SetPermissions();
    }

    /**
     * Create an instance of {@link SetPermissionsResult }
     * 
     */
    public SetPermissionsResult createSetPermissionsResult() {
        return new SetPermissionsResult();
    }

    /**
     * Create an instance of {@link ImplementationException }
     * 
     */
    public ImplementationException createImplementationException() {
        return new ImplementationException();
    }

    /**
     * Create an instance of {@link GetClientIdentity }
     * 
     */
    public GetClientIdentity createGetClientIdentity() {
        return new GetClientIdentity();
    }

    /**
     * Create an instance of {@link AddPermissions }
     * 
     */
    public AddPermissions createAddPermissions() {
        return new AddPermissions();
    }

    /**
     * Create an instance of {@link DefineRole }
     * 
     */
    public DefineRole createDefineRole() {
        return new DefineRole();
    }

    /**
     * Create an instance of {@link UndefinePermissionResult }
     * 
     */
    public UndefinePermissionResult createUndefinePermissionResult() {
        return new UndefinePermissionResult();
    }

    /**
     * Create an instance of {@link AddPermissions.PermissionNames }
     * 
     */
    public AddPermissions.PermissionNames createAddPermissionsPermissionNames() {
        return new AddPermissions.PermissionNames();
    }

    /**
     * Create an instance of {@link UpdateClientIdentityResult }
     * 
     */
    public UpdateClientIdentityResult createUpdateClientIdentityResult() {
        return new UpdateClientIdentityResult();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link DuplicatePermissionException }
     * 
     */
    public DuplicatePermissionException createDuplicatePermissionException() {
        return new DuplicatePermissionException();
    }

    /**
     * Create an instance of {@link AddPermissionsResult }
     * 
     */
    public AddPermissionsResult createAddPermissionsResult() {
        return new AddPermissionsResult();
    }

    /**
     * Create an instance of {@link SetRoles }
     * 
     */
    public SetRoles createSetRoles() {
        return new SetRoles();
    }

    /**
     * Create an instance of {@link UndefinePermission }
     * 
     */
    public UndefinePermission createUndefinePermission() {
        return new UndefinePermission();
    }

    /**
     * Create an instance of {@link UpdatePermission }
     * 
     */
    public UpdatePermission createUpdatePermission() {
        return new UpdatePermission();
    }

    /**
     * Create an instance of {@link DuplicateRoleException }
     * 
     */
    public DuplicateRoleException createDuplicateRoleException() {
        return new DuplicateRoleException();
    }

    /**
     * Create an instance of {@link ClientIdentityValidationException }
     * 
     */
    public ClientIdentityValidationException createClientIdentityValidationException() {
        return new ClientIdentityValidationException();
    }

    /**
     * Create an instance of {@link NoSuchClientIdentityException }
     * 
     */
    public NoSuchClientIdentityException createNoSuchClientIdentityException() {
        return new NoSuchClientIdentityException();
    }

    /**
     * Create an instance of {@link NoSuchPermissionException }
     * 
     */
    public NoSuchPermissionException createNoSuchPermissionException() {
        return new NoSuchPermissionException();
    }

    /**
     * Create an instance of {@link GetRole }
     * 
     */
    public GetRole createGetRole() {
        return new GetRole();
    }

    /**
     * Create an instance of {@link RemoveRolesResult }
     * 
     */
    public RemoveRolesResult createRemoveRolesResult() {
        return new RemoveRolesResult();
    }

    /**
     * Create an instance of {@link DuplicateClientIdentityException }
     * 
     */
    public DuplicateClientIdentityException createDuplicateClientIdentityException() {
        return new DuplicateClientIdentityException();
    }

    /**
     * Create an instance of {@link NoSuchRoleException }
     * 
     */
    public NoSuchRoleException createNoSuchRoleException() {
        return new NoSuchRoleException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemovePermissions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "RemovePermissions")
    public JAXBElement<RemovePermissions> createRemovePermissions(RemovePermissions value) {
        return new JAXBElement<RemovePermissions>(_RemovePermissions_QNAME, RemovePermissions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UndefinePermission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "UndefinePermission")
    public JAXBElement<UndefinePermission> createUndefinePermission(UndefinePermission value) {
        return new JAXBElement<UndefinePermission>(_UndefinePermission_QNAME, UndefinePermission.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPermissions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "AddPermissions")
    public JAXBElement<AddPermissions> createAddPermissions(AddPermissions value) {
        return new JAXBElement<AddPermissions>(_AddPermissions_QNAME, AddPermissions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetVendorVersionResult")
    public JAXBElement<String> createGetVendorVersionResult(String value) {
        return new JAXBElement<String>(_GetVendorVersionResult_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicateClientIdentityException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "DuplicateClientIdentityException")
    public JAXBElement<DuplicateClientIdentityException> createDuplicateClientIdentityException(DuplicateClientIdentityException value) {
        return new JAXBElement<DuplicateClientIdentityException>(_DuplicateClientIdentityException_QNAME, DuplicateClientIdentityException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoSuchClientIdentityException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "NoSuchClientIdentityException")
    public JAXBElement<NoSuchClientIdentityException> createNoSuchClientIdentityException(NoSuchClientIdentityException value) {
        return new JAXBElement<NoSuchClientIdentityException>(_NoSuchClientIdentityException_QNAME, NoSuchClientIdentityException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefineRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "DefineRole")
    public JAXBElement<DefineRole> createDefineRole(DefineRole value) {
        return new JAXBElement<DefineRole>(_DefineRole_QNAME, DefineRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImplementationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "ImplementationException")
    public JAXBElement<ImplementationException> createImplementationException(ImplementationException value) {
        return new JAXBElement<ImplementationException>(_ImplementationException_QNAME, ImplementationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientIdentity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetClientIdentity")
    public JAXBElement<GetClientIdentity> createGetClientIdentity(GetClientIdentity value) {
        return new JAXBElement<GetClientIdentity>(_GetClientIdentity_QNAME, GetClientIdentity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ACClientIdentity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetClientIdentityResult")
    public JAXBElement<ACClientIdentity> createGetClientIdentityResult(ACClientIdentity value) {
        return new JAXBElement<ACClientIdentity>(_GetClientIdentityResult_QNAME, ACClientIdentity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetClientIdentityNamesResult")
    public JAXBElement<ArrayOfString> createGetClientIdentityNamesResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetClientIdentityNamesResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetClientIdentityNames")
    public JAXBElement<EmptyParms> createGetClientIdentityNames(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetClientIdentityNames_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "AddRoles")
    public JAXBElement<AddRoles> createAddRoles(AddRoles value) {
        return new JAXBElement<AddRoles>(_AddRoles_QNAME, AddRoles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetPermissionNames")
    public JAXBElement<EmptyParms> createGetPermissionNames(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetPermissionNames_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicateRoleException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "DuplicateRoleException")
    public JAXBElement<DuplicateRoleException> createDuplicateRoleException(DuplicateRoleException value) {
        return new JAXBElement<DuplicateRoleException>(_DuplicateRoleException_QNAME, DuplicateRoleException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoSuchRoleException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "NoSuchRoleException")
    public JAXBElement<NoSuchRoleException> createNoSuchRoleException(NoSuchRoleException value) {
        return new JAXBElement<NoSuchRoleException>(_NoSuchRoleException_QNAME, NoSuchRoleException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoSuchPermissionException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "NoSuchPermissionException")
    public JAXBElement<NoSuchPermissionException> createNoSuchPermissionException(NoSuchPermissionException value) {
        return new JAXBElement<NoSuchPermissionException>(_NoSuchPermissionException_QNAME, NoSuchPermissionException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetRole")
    public JAXBElement<GetRole> createGetRole(GetRole value) {
        return new JAXBElement<GetRole>(_GetRole_QNAME, GetRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientIdentityValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "ClientIdentityValidationException")
    public JAXBElement<ClientIdentityValidationException> createClientIdentityValidationException(ClientIdentityValidationException value) {
        return new JAXBElement<ClientIdentityValidationException>(_ClientIdentityValidationException_QNAME, ClientIdentityValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UndefineClientIdentity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "UndefineClientIdentity")
    public JAXBElement<UndefineClientIdentity> createUndefineClientIdentity(UndefineClientIdentity value) {
        return new JAXBElement<UndefineClientIdentity>(_UndefineClientIdentity_QNAME, UndefineClientIdentity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetVendorVersion")
    public JAXBElement<EmptyParms> createGetVendorVersion(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetVendorVersion_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateClientIdentity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "UpdateClientIdentity")
    public JAXBElement<UpdateClientIdentity> createUpdateClientIdentity(UpdateClientIdentity value) {
        return new JAXBElement<UpdateClientIdentity>(_UpdateClientIdentity_QNAME, UpdateClientIdentity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientPermissionNames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetClientPermissionNames")
    public JAXBElement<GetClientPermissionNames> createGetClientPermissionNames(GetClientPermissionNames value) {
        return new JAXBElement<GetClientPermissionNames>(_GetClientPermissionNames_QNAME, GetClientPermissionNames.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PermissionValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "PermissionValidationException")
    public JAXBElement<PermissionValidationException> createPermissionValidationException(PermissionValidationException value) {
        return new JAXBElement<PermissionValidationException>(_PermissionValidationException_QNAME, PermissionValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "UpdateRole")
    public JAXBElement<UpdateRole> createUpdateRole(UpdateRole value) {
        return new JAXBElement<UpdateRole>(_UpdateRole_QNAME, UpdateRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnsupportedOperationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "UnsupportedOperationException")
    public JAXBElement<UnsupportedOperationException> createUnsupportedOperationException(UnsupportedOperationException value) {
        return new JAXBElement<UnsupportedOperationException>(_UnsupportedOperationException_QNAME, UnsupportedOperationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetStandardVersionResult")
    public JAXBElement<String> createGetStandardVersionResult(String value) {
        return new JAXBElement<String>(_GetStandardVersionResult_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "RemoveRoles")
    public JAXBElement<RemoveRoles> createRemoveRoles(RemoveRoles value) {
        return new JAXBElement<RemoveRoles>(_RemoveRoles_QNAME, RemoveRoles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetRoleNamesResult")
    public JAXBElement<ArrayOfString> createGetRoleNamesResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetRoleNamesResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetStandardVersion")
    public JAXBElement<EmptyParms> createGetStandardVersion(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetStandardVersion_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicatePermissionException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "DuplicatePermissionException")
    public JAXBElement<DuplicatePermissionException> createDuplicatePermissionException(DuplicatePermissionException value) {
        return new JAXBElement<DuplicatePermissionException>(_DuplicatePermissionException_QNAME, DuplicatePermissionException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ALEException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "ALEException")
    public JAXBElement<ALEException> createALEException(ALEException value) {
        return new JAXBElement<ALEException>(_ALEException_QNAME, ALEException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefineClientIdentity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "DefineClientIdentity")
    public JAXBElement<DefineClientIdentity> createDefineClientIdentity(DefineClientIdentity value) {
        return new JAXBElement<DefineClientIdentity>(_DefineClientIdentity_QNAME, DefineClientIdentity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetClientPermissionNamesResult")
    public JAXBElement<ArrayOfString> createGetClientPermissionNamesResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetClientPermissionNamesResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "SecurityException")
    public JAXBElement<SecurityException> createSecurityException(SecurityException value) {
        return new JAXBElement<SecurityException>(_SecurityException_QNAME, SecurityException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UndefineRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "UndefineRole")
    public JAXBElement<UndefineRole> createUndefineRole(UndefineRole value) {
        return new JAXBElement<UndefineRole>(_UndefineRole_QNAME, UndefineRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePermission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "UpdatePermission")
    public JAXBElement<UpdatePermission> createUpdatePermission(UpdatePermission value) {
        return new JAXBElement<UpdatePermission>(_UpdatePermission_QNAME, UpdatePermission.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPermission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetPermission")
    public JAXBElement<GetPermission> createGetPermission(GetPermission value) {
        return new JAXBElement<GetPermission>(_GetPermission_QNAME, GetPermission.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetPermissions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "SetPermissions")
    public JAXBElement<SetPermissions> createSetPermissions(SetPermissions value) {
        return new JAXBElement<SetPermissions>(_SetPermissions_QNAME, SetPermissions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RoleValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "RoleValidationException")
    public JAXBElement<RoleValidationException> createRoleValidationException(RoleValidationException value) {
        return new JAXBElement<RoleValidationException>(_RoleValidationException_QNAME, RoleValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetSupportedOperations")
    public JAXBElement<EmptyParms> createGetSupportedOperations(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetSupportedOperations_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetRoleNames")
    public JAXBElement<EmptyParms> createGetRoleNames(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetRoleNames_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetSupportedOperationsResult")
    public JAXBElement<ArrayOfString> createGetSupportedOperationsResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetSupportedOperationsResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetPermissionNamesResult")
    public JAXBElement<ArrayOfString> createGetPermissionNamesResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetPermissionNamesResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ACPermission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetPermissionResult")
    public JAXBElement<ACPermission> createGetPermissionResult(ACPermission value) {
        return new JAXBElement<ACPermission>(_GetPermissionResult_QNAME, ACPermission.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "SetRoles")
    public JAXBElement<SetRoles> createSetRoles(SetRoles value) {
        return new JAXBElement<SetRoles>(_SetRoles_QNAME, SetRoles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefinePermission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "DefinePermission")
    public JAXBElement<DefinePermission> createDefinePermission(DefinePermission value) {
        return new JAXBElement<DefinePermission>(_DefinePermission_QNAME, DefinePermission.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ACRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aleac:wsdl:1", name = "GetRoleResult")
    public JAXBElement<ACRole> createGetRoleResult(ACRole value) {
        return new JAXBElement<ACRole>(_GetRoleResult_QNAME, ACRole.class, null, value);
    }

}
