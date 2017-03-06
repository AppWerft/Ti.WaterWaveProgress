/* C++ code produced by gperf version 3.0.3 */
/* Command-line: /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/gperf -L C++ -E -t /private/var/folders/dp/_57t8_6j247dh0h7mknf97600000gn/T/fuerst/waterwaveprogress-generated/KrollGeneratedBindings.gperf  */
/* Computed positions: -k'' */

#line 3 "/private/var/folders/dp/_57t8_6j247dh0h7mknf97600000gn/T/fuerst/waterwaveprogress-generated/KrollGeneratedBindings.gperf"


#include <string.h>
#include <v8.h>
#include <KrollBindings.h>

#include "ti.waterwaveprogress.WaterwaveprogressModule.h"
#include "ti.waterwaveprogress.ViewProxy.h"


#line 14 "/private/var/folders/dp/_57t8_6j247dh0h7mknf97600000gn/T/fuerst/waterwaveprogress-generated/KrollGeneratedBindings.gperf"
struct titanium::bindings::BindEntry;
/* maximum key range = 15, duplicates = 0 */

class WaterwaveprogressBindings
{
private:
  static inline unsigned int hash (const char *str, unsigned int len);
public:
  static struct titanium::bindings::BindEntry *lookupGeneratedInit (const char *str, unsigned int len);
};

inline /*ARGSUSED*/
unsigned int
WaterwaveprogressBindings::hash (register const char *str, register unsigned int len)
{
  return len;
}

struct titanium::bindings::BindEntry *
WaterwaveprogressBindings::lookupGeneratedInit (register const char *str, register unsigned int len)
{
  enum
    {
      TOTAL_KEYWORDS = 2,
      MIN_WORD_LENGTH = 30,
      MAX_WORD_LENGTH = 44,
      MIN_HASH_VALUE = 30,
      MAX_HASH_VALUE = 44
    };

  static struct titanium::bindings::BindEntry wordlist[] =
    {
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""},
#line 17 "/private/var/folders/dp/_57t8_6j247dh0h7mknf97600000gn/T/fuerst/waterwaveprogress-generated/KrollGeneratedBindings.gperf"
      {"ti.waterwaveprogress.ViewProxy", ::ti::waterwaveprogress::waterwaveprogress::ViewProxy::bindProxy, ::ti::waterwaveprogress::waterwaveprogress::ViewProxy::dispose},
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""}, {""},
#line 16 "/private/var/folders/dp/_57t8_6j247dh0h7mknf97600000gn/T/fuerst/waterwaveprogress-generated/KrollGeneratedBindings.gperf"
      {"ti.waterwaveprogress.WaterwaveprogressModule", ::ti::waterwaveprogress::WaterwaveprogressModule::bindProxy, ::ti::waterwaveprogress::WaterwaveprogressModule::dispose}
    };

  if (len <= MAX_WORD_LENGTH && len >= MIN_WORD_LENGTH)
    {
      unsigned int key = hash (str, len);

      if (key <= MAX_HASH_VALUE)
        {
          register const char *s = wordlist[key].name;

          if (*str == *s && !strcmp (str + 1, s + 1))
            return &wordlist[key];
        }
    }
  return 0;
}
#line 18 "/private/var/folders/dp/_57t8_6j247dh0h7mknf97600000gn/T/fuerst/waterwaveprogress-generated/KrollGeneratedBindings.gperf"

