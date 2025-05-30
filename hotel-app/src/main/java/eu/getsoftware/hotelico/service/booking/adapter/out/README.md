Encapsulation of Adapters:

Impl classes should not be public,
but they should be @Service injectable for Spring context:
 - these *impl only for injection, but never should be called directly as public methods!
 - The autowiring works regardless of the visibility modifiers.